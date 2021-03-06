package DTree.program;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 决策树算法的Reducer类。 输入：<key,value>对，其中key是一个复合类型， 具体为key = <条件号#属性号，属性值，元素类标号>
 * value = 1. 功能：将同一个key下的所有value值相加，即进行求和操作，
 * 决策树算法的Reducer类。
 * 输入：<key,value>对，其中key是一个复合类型， 具体为key =
 *       <条件号#属性号，属性值，元素类标号> value = 1.
 * 功能：将同一个key下的所有value值相加，即进行求和操作，
 * 输出：key仍保持输入的Key，value是输入的value列表中所有值的求和。
 * 
 * 该Reducer是MapReduce应用中常见的一种Reducer的类型，
 * 其功能是将同Key的所有Int类型的Value进行求和，作为该Key的新的Value。
 * 在WordCount的程序中，用到的Reducer就是该类型Reducer。
 * Hadoop为这种类型的应用提供了一个标准的“IntSumReducer.class”，
 * 读者在实现时，可以直接在配置中使用该Reducer，而不再需要单独编写Reducer代码。
 */

public class DecisionTreeReducer extends
    Reducer<Text, IntWritable, Text, IntWritable> {
  /* 重载 reduce 函数 */
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
    int sum = 0;// 保存部分和
    // 读取每一个value的值并进行累加
    for (IntWritable value : values) {
      sum += value.get();
    }
    // 最终输出累加和
    context.write(key, new IntWritable(sum));
  }
}
