package com.surging.sqoop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
/**
 * 使用hadoop api实现hadoop文件和目录的操作
 *
 */
public class TestHdfs {
	FileSystem fs=null;
	public static void main(String[] args) throws IOException {
		//1.获取默认配置
		Configuration conf=new Configuration();
		//2.设置NameNode的地址
		conf.set("fs.defaultFS", "hdfs://192.168.0.95:9000");
		//3.获取文件系统对象，会根据配置返回对应的文件系统实现类
		FileSystem fs = FileSystem.get(conf);
		//4.根据FileSystem获取文件输入流
		FSDataInputStream fis = fs.open(new Path("/user/trinity/test/part-m-00000"));
		FileOutputStream fos = new FileOutputStream("e:/hadoopTest.txt");
		//5.将输入流的内容，输出到输出流
		IOUtils.copyBytes(fis, fos, conf);
	}
	@Before
	public void init() throws IOException, InterruptedException, URISyntaxException{
		//1.获取默认配置
		Configuration conf=new Configuration();
		//2.设置NameNode的地址
		conf.set("fs.defaultFS", "hdfs://192.168.0.95:9000");
		//3.获取文件系统对象，会根据配置返回对应的文件系统实现类
		fs = FileSystem.get(new URI("hdfs://192.168.0.95:9000"),conf,"hadoop");
		
	}
	//测试copyToLocal
	@Test
	public void download() throws IllegalArgumentException, IOException{
		//使用以下方式会出错，因为hadoop会执行Linux的命令来删除源文件，导致出错
//		fs.copyToLocalFile(new Path("/test.txt"), new Path("e:/nn.txt"));
		//设置不删除来源文件，并指定使用的目标文件是本地文件系统
		fs.copyToLocalFile(false, new Path("/test.txt"), new Path("e:/nn.txt"),true);
	}
	
	@Test
	public void upload() throws IllegalArgumentException, IOException{
		//上传时，默认会使用操作系统的登录用户作为上传的用户，会导致没有权限的异常
		//需要模拟式hadoop用户来处理  fs = FileSystem.get(new URI("hdfs://hadoop01:9000"),conf,"hadoop");
		fs.copyFromLocalFile(new Path("e:/hadoop-2.7.6.tar.gz"), new Path("/hadoop-2.7.6.tar.gz"));
	}
	//列出目录下面的文件
	@Test
	public void ListDirFiles() throws FileNotFoundException, IllegalArgumentException, IOException{
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);
		while(files.hasNext()){
			LocatedFileStatus file=files.next();
			System.out.println(file.getPath()+"-----"+file.getPath().getName());
		}
	}
	@Test
	public void deleteFile() throws IllegalArgumentException, IOException{
		//第二个参数，是否递归删除子目录
		fs.delete(new Path("/hadoop-2.7.6.tar.gz"),true);
	}
}
