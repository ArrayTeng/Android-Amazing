补间动画和逐帧动画统称为视图动画作用的对象是控件实例，属性动画作用的对象是控件的属性,本次学习的重点在于属性动画不在刻意学习视图动画。

##### ValueAnimator

1. ValueAnimator 只负责对指定值区间进行动画操作,我们需要对运算过程进程监听以便于对控件执行动画操作,在执行动画操作中必须记得在 Activity 被销毁的时候执行取消动画的操作，否则会出现内存泄露。
2. 自定义插值器，插值器是用来控制动画的区间值是如何被计算出来的，在属性动画中常见的插值器有 LinearInterpolator(匀速返回区间的值)、DecelerateInterpolator(开始变化快，后期变化慢),自定义插值器需要实现 Interpolator 或者
 TimeInterpolator 接口，而 Interpolator 接口继承自 TimeInterpolator，所以只需要继承 TimeInterpolator 就可以了。
 ```
 /**
  * A time interpolator defines the rate of change of an animation. This allows animations
  * to have non-linear motion, such as acceleration and deceleration.
  */
 public interface TimeInterpolator {

     /**
      * Maps a value representing the elapsed fraction of an animation to a value that represents
      * the interpolated fraction. This interpolated value is then multiplied by the change in
      * value of an animation to derive the animated value at the current elapsed animation time.
      *
      * @param input A value between 0 and 1.0 indicating our current point
      *        in the animation where 0 represents the start and 1.0 represents
      *        the end
      * @return The interpolation value. This value can be more than 1.0 for
      *         interpolators which overshoot their targets, or less than 0 for
      *         interpolators that undershoot their targets.
      */
     float getInterpolation(float input);
 }

 ```

 查看 TimeInterpolator 源码你会发现里面只有一个方法 getInterpolation ，getInterpolation 的参数 input 表示动画当前的进度，它的取值范围是 0 ～ 1 ，取 0 时表示动画刚开始，取 1 时表示动画结束，取 0.5 时表示动画进行了一半，
 getInterpolation 方法的返回值表示实际想要显示的进度，取值可以超过 1 也可以小于 0 ，超过 1 表示已经超过目标值，小于 0 表示小于开始的位置，比如说一个动画表示从 100 到 400 的数值变化，它的动画当前进度这么计算 ： 动画当前进度 = 100 + （400 -100）* input

 3. Evaluator Evaluator可以看作是一个转换器用来把小数进度转换成对应的数值位置，只有在定义动画时的数值类型与 Evaluator 的返回的值类型一样，才能使用这个 Evaluator，自定义 Evaluator 只需要实现 TypeEvaluator 接口即可

 ofInt(0,400) -> 插值器（返回当前数值进度）-> Evaluator（根据数值进度计算当前值）-> 监听器返回