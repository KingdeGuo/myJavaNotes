[toc]

# Java集合部分拾遗

Iterator和ListIterator有什么区别

- Iterator可以遍历Set和List集合，而ListIterator只能遍历List。
- Iterator只能单向遍历，而ListIterator可以双向遍历。
- ListIterator实现了Iterator接口，然后添加了一些额外的功能，比如添加一个元素，替换一个元素，获取前面或后面元素索引的位置。

如何在数组和List之间转换

- 数组转List：Arrays.asList(arr);
- List转数组：使用List自带的toArray();

