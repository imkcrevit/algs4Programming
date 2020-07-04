package training.com;

import edu.princeton.cs.algs4.StdOut;


public class FirstTraining {
    public static void main(String[] args) {
        String[] a=new String[]{"A","E","Q","S","U","Y","E","I","N","O","S","T"};
        String[] b = new String[]{"R","B","W","W","R","W","W","B","R","R","W","B","R"};
//        Merga.sort(a);
//        Merga.show(a);
        Quick.sort(a);
        Quick.show(a);
    }
    //归并排序-自顶而下的归并排序
    public static class Merga{
        private static Comparable[] aux;

        public static void merge(Comparable[] a ,int lo,int mid,int hi){
            int i=lo;
            int j=mid+1;
            for(int k=lo;k<=hi;k++){
                aux[k] = a[k];
            }
            //此处进行全部数组的遍历,最小值为:lo 最大值:hi
            for(int k=lo;k<=hi;k++){
                if(i>mid) a[k]=aux[j++]; //1. 当lo>mid时 取右半边元素 此时a[k]=a[j] j自加
                else if(j>hi) a[k]=aux[i++];//2. 当mid+1>hi时 取左半边元素 此时a[k]=a[i] i自加
                else if(less(aux[j],aux[i])) a[k]=aux[j++];//3. 将a[j]<a[i]时,a[k]=a[j] j自加
                else a[k]=aux[i++];//4. 反之,a[k]=a[i] i自加
            }
            /*
             循环的意思为:
             当左半区域第一位与右半区域第一位进行比较如果右侧大于左侧,则互换位置
             此时i不停自加,当i的值大于mid中间值的时候代表左侧区域值全部取完,此时取右侧元素
             j同理
             */
//            StdOut.print("Merga -- lo:"+lo+" mid: "+mid+" hi: "+hi+"\r\n");
        }

        public static void sort(Comparable[] a){
            aux=new Comparable[a.length];
            sort(a,0,a.length-1);
        }
        public static void sort(Comparable[] a ,int lo,int hi){
            if(hi<=lo) return;
            int mid = lo+(hi-lo)/2;
            StdOut.print("TT -- lo:"+lo+" mid: "+mid+" hi: "+hi+"\r\n");
            sort(a,lo,mid);//此处递归值,将从0,5,11依次递减至0,0,1停止,此时运行第二个递归将从0,0,1开始,第二个递归接收值使用hi<lo直到0,2,5
            StdOut.print("First Sort -- lo:"+lo+" mid: "+mid+" hi: "+hi+"\r\n");
            sort(a,mid+1,hi);//此处递归值,有人前期可能会理解为统一层面,不明白hi为何会一直变化,他发挥作用其实是在a,lo,mid的值
            StdOut.print("Second Sort -- lo:"+lo+" mid: "+mid+" hi: "+hi+"\r\n");
            merge(a,lo,mid,hi);
            /*
            由于此结构相当于三层递归,所以会产生一定的理解误差,第二个sort函数sort(a,mid+1,hi)其实一直在第一个函数之下,可以理解成
            sort(a,lo,mid){sort(a,mid+1,hi)},通过将满足的数组不断遍历 从而比较大小进行互换
             */
        }
        private static  boolean less(Comparable v,Comparable w){
            return v.compareTo(w)<0;
        }
        private static void show(Comparable[] a ){
            for(int i=0;i<a.length;i++){
                StdOut.print(a[i]+" ");
            }
            StdOut.println();
        }
        private static boolean isSorted(Comparable[] a){
            for(int i=1;i<a.length;i++)
                if(less(a[i],a[i-1])) return false;
            return true;
        }
    }
    //快速排序
    public static class Quick{

        private static Comparable[] aux;

        public static void sort(Comparable[] a){
            aux=new Comparable[a.length];
            sort(a,0,a.length-1);
        }
        public static void sort(Comparable[] a ,int lo,int hi){
            if(hi<=lo) return ;
            int lt=lo,i=lo+1,gt=hi;
            Comparable v = a[lo];//随机值,设置数组起点
            while(i<=gt){
                int cmp = a[i].compareTo(v);
                if(cmp<0) exch(a,lt++,i++);//如果a[i]<a[lo] 交换a[lt]-->a[lo]与a[i]-->a[lo+1],相应参数运算后自加
                else if(cmp>0) exch(a,i,gt--);//反之,交换a[i]与a[gt],当道第三个指针中,gt自减~指针左移,转换位置之后i不变再次循环将继续判断a[i]
                else i++;
                StdOut.print("while statement lt: "+lt+" i:"+i+" gt:"+gt+"\r\n");
            }
            sort(a,lo,lt-1);//运行完第一次之后,将划分为三段<a[lo];=a[lo];>a[lo],此时针对,a[lo]部分进行排序,逐步递归,将左侧区域位置变为有序数组
            StdOut.print("second lo:"+lo+" lt-1:"+(lt-1)+"\r\n");
            sort(a,gt+1,hi);//gt逐渐减少会逐渐少于hi
            StdOut.print("third gt+1:"+(gt+1)+" hi:"+hi+"\r\n");
        }
       private static void exch(Comparable[] a ,int lo,int hi){
            Comparable tmp ;
            tmp=a[lo];
            a[lo]=a[hi];
            a[hi]=tmp;
       }
        private static void show(Comparable[] a ){
            for(int i=0;i<a.length;i++){
                StdOut.print(a[i]+" ");
            }
            StdOut.println();
        }
    }

}

