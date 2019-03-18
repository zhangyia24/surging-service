package com.surging.testMR;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *	reducer类，负责对map计算结果合并处理
 *	需要设置4个泛型，分别是输入key、value的类型，输出的key、value的类型
 *
 */
public class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Context context) throws IOException, InterruptedException {

			long count=0;
			//遍历values，进行合计
			for(LongWritable val:values){
				count+=val.get();
			}
			//输出到文件冲
			context.write(key,new LongWritable(count));
		
	}
	
}
