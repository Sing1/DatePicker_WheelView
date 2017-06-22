# DatePicker
![](app/src/main/res/mipmap-xhdpi/demo.gif "")  

### 轻量级的日期选择器，使用WheelView  
#### 自定义属性介绍  
属性|介绍  
---|---  
lineColor|分割线颜色
lineHeight|分割线高度
itemNumber|此wheelView显示item的个数
maskHight|蒙版高度（normalText的位置）
noEmpty|设置true则选中不能为空，否则可以是空
normalTextColor|未选中文本颜色
normalTextSize|未选中文本字体大小
selectedTextColor|选中文本颜色
selectedTextSize|选中文本字体大小
unitHight|每个item单元的高度  
#### API介绍  
```JAVA
//设置WheelView的数据
setData(ArrayList data)
//重置 WheelView的数据，如果已经设置过的话
resetData(ArrayList data)
//获取选中项的index
int getSelected()
//获取选中项的文本信息
String getSelectedText()
//获取WheelView是否在滚动中
boolean isScrolling
//获取wheelView是否可用
boolean isEnable()
//设置WheelView是否可用
void setEnable(boolean isEnable)
//设置默认选中项的index
void setDefault(int index)
//获取WheelView的item项个数
int getListSize()
//获取index位置上的文本数据
String getItemText(int index)
//对WheelView设置监听，在 滑动过程 或者 滑动停止 返回数据信息。
void setOnSelectListener(OnSelectListener onSelectListener)
```