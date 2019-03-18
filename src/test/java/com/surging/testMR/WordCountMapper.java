package com.surging.testMR;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 *	WordCount例子
 */
//需要设置4个泛型，分别是输入的key、value的类型，输出的key、value的类型
//文本输入的key的类型是行的偏移量（长整型），value是字符串
//输出的key是单词，value是单词的计数
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	@Override
	//map函数，key是文件中的每行的偏移量，value是每行的内容；参数context是map的上下文，通过context将结果传给缓冲区
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//具体业务方法在map函数中编写
		//得到每行的内容
		String line = value.toString();
		
		//对每行的内容进行分拆，得到单词
		String[] words = line.split(" ");
		
		//遍历数组，生成key和value
		for(String word:words){
			//将key和value输入到context中
			context.write(new Text(word), new LongWritable(1));
		}
	}
	
	
}
