package com.yzg.blog.portal.controller;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.yzg.blog.portal.controller.dto.ArticleDTO;
import com.yzg.blog.portal.controller.dto.CategoryDTO;
import com.yzg.blog.portal.controller.dto.UserDTO;
import com.yzg.blog.portal.service.ArticleService;
import com.yzg.blog.portal.service.CategoryService;
import com.yzg.blog.portal.service.TagService;
import com.yzg.blog.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Validated
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    @Resource
    ArticleService articleService;
    @Resource
    CategoryService categoryService;
    @Resource
    UserService userService;
    @Resource
    TagService tagService;

    @RequestMapping(value = "/user/{num}", method = RequestMethod.POST)
    public String insertUser(@PathVariable Integer num) {
        UserDTO dto = new UserDTO();
        for (int i = 0; i < num; i ++) {
            dto.setEmail(RandomUtil.randomString(3) + i + "@test.com");
            String pwd = "123456";
            dto.setPassword(pwd);
            dto.setConfirmPassword(pwd);
            userService.register(dto);
        }
        return "success";
    }

    @RequestMapping(value = "/tags/{num}", method = RequestMethod.POST)
    public String insertTags(@PathVariable Integer num) {
        CategoryDTO dto = new CategoryDTO();
        for (int i = 0; i < num; i ++) {
            String title = RandomUtil.randomString(5) + i;
            dto.setTitle(title);
            dto.setAlias(title);
            tagService.addTag(dto);
        }
        return "success";
    }
    @RequestMapping(value = "/article/{num}", method = RequestMethod.POST)
    public String insertArticle(@PathVariable Integer num) {

        ArticleDTO dto = new ArticleDTO();
        for (int i = 0; i < num; i ++) {
            dto.setUserId(12743235 + i % 1000);
            dto.setCategoryId(1009 + i % 5);
            dto.setCover(true);
            dto.setTitle("文章标题" + RandomUtil.randomString(3));
            dto.setCoverUrl("http://youzijie-1255502425.cos.ap-chengdu.myqcloud.com/sonova/1583468037414.PNG");
            dto.setTagIds(Lists.newArrayList(1004,1005,1006));
            String html = "<p>前言：本文总结自《深入理解Java虚拟机》第三版，第2章 Java内存区域与内存溢出异常</p><h2>运行时数据区域</h2><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。根据《Java虚拟机规范》的规定，Java虚拟机管理的内存包括以下几个区域：<br><img title='运行时数据区' alt='运行时数据区' src='https://user-gold-cdn.xitu.io/2020/6/11/172a113fbb06bbae?w=937&amp;h=740&amp;f=png&amp;s=306287'></p><p><br></p><h3>程序计数器</h3>&nbsp; &nbsp; &nbsp; &nbsp;程序计数器（Program Counter Register）是一块较小的内存空间，它可以看作是当前线程所执行的 字节码的行号指示器。<p></p>&nbsp; &nbsp; &nbsp; &nbsp;由于Java虚拟机的多线程是通过线程轮流切换、分配处理器执行时间的方式来实现的，在任何一 个确定的时刻，一个处理器（对于多核处理器来说是一个内核）都只会执行一条线程中的指令。因此，为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，各条线程之间计数器互不影响，独立存储，我们称这类内存区域为“线程私有”的内存。<div><p></p>&nbsp; &nbsp; &nbsp; &nbsp;如果线程正在执行的是一个Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是本地（Native）方法，这个计数器值则应为空（Undefined）。<br>此内存区域是唯一一个不会产生<a href='https://docs.oracle.com/javase/8/docs/api/java/lang/OutOfMemoryError.html'>OutOfMemoryError</a>情况的区域。<h3>Java虚拟机栈</h3>&nbsp; &nbsp; &nbsp; &nbsp;与程序计数器一样，Java虚拟机栈（Java Virtual Machine Stack）也是线程私有的，它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的线程内存模型：每个方法被执行的时候，Java虚拟机都会同步创建一个栈帧（Stack Frame）用于存储局部变量表、操作数栈、动态连接、方法出口等信 息。每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。<div><p></p><div>&nbsp; &nbsp; &nbsp; &nbsp;局部变量表存放了编译期可知的各种Java虚拟机基本数据类型（boolean、byte、char、short、int、 float、long、double）、对象引用（reference类型，它并不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或者其他与此对象相关的位置）和returnAddress 类型（指向了一条字节码指令的地址）。<p></p>&nbsp; &nbsp; &nbsp; &nbsp;在《Java虚拟机规范》中，对这个内存区域规定了两类异常状况：如果线程请求的栈深度大于虚拟机所允许的深度，将抛出<a href='https://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html'>StackOverflowError</a>异常；如果Java虚拟机栈容量可以动态扩展（ HotSpot虚拟机的栈容量是不可以动态扩展的），当栈扩展时无法申请到足够的内存会抛出OutOfMemoryError异常。<h3>本地方法栈</h3>&nbsp; &nbsp; &nbsp; &nbsp;本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别只是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的本地（Native）方法服务。&nbsp;<p></p><div>&nbsp; &nbsp; &nbsp; &nbsp;与虚拟机栈一样，本地方法栈也会在栈深度溢出或者栈扩展失败时分别抛出StackOverflowError和OutOfMemoryError异常。<h3>方法区</h3>&nbsp; &nbsp; &nbsp; &nbsp;方法区（Method Area）与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据。<p></p><div><div><div><div><div><p>&nbsp; &nbsp; &nbsp; &nbsp;在JDK1.8以前，当时的HotSpot虚拟机设计团队选择把收集器的分代设计扩展至方法区，或者说使用永久代来实现方法区，这样使得 HotSpot的垃圾收集器能够像管理Java堆一样管理这部分内存。对于其他虚拟机（IMB J9和BEA JRockit等）实现来说，是不存在永久代的概念的。使用永久代实现方法区不是一个好主意，这种设计导致了Java应用更容易遇到内存溢出的问题（永久代有-XX：MaxPermSize的上限，即使不设置也有默认大小，而J9和JRockit只要没有触碰到进程可用内存的上限，例如32位系统中的4GB限制，就不会出问题），而且有极少数方法 （例如String::intern()）会因永久代的原因而导致不同虚拟机下有不同的表现。<br></p><div><div><p>&nbsp; &nbsp; &nbsp; &nbsp;在JDK 6的时候HotSpot开发团队就有放弃永久代，逐步改为采用本地内存（Native Memory）来实现方法区的计划了，到了JDK 7的HotSpot，已经把原本放在永久代的字符串常量池、静态变量等移出，而到了 JDK 8，终于完全废弃了永久代的概念，改用与JRockit、J9一样在本地内存中实现的元空间（Metaspace）来代替，把JDK 7中永久代还剩余的内容（主要是类型信息）全部移到元空间中。</p>&nbsp; &nbsp; &nbsp; &nbsp;根据《Java虚拟机规范》的规定，如果方法区无法满足新的内存分配需求时，将抛出 OutOfMemoryError异常。&nbsp;<h3>运行时常量池</h3>&nbsp; &nbsp; &nbsp; &nbsp;运行时常量池（Runtime Constant Pool）是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池表（Constant Pool Table），用于存放编译期生成的各种字面量与符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。&nbsp;<p></p>&nbsp; &nbsp; &nbsp; &nbsp;和方法区一样，当常量池无法再申请到内存时会抛出OutOfMemoryError异常。&nbsp;<h3>对象的创建</h3>&nbsp; &nbsp; &nbsp; &nbsp;当使用new创建对象时，Java虚拟机首先将检查这个对象能否在常量池中定位到一个类的符号引用，并检查引用代表的类是否已被加载，解析和初始化。如果没有，则必须先执行类加载过程。<p></p>&nbsp; &nbsp; &nbsp; &nbsp;当类加载检查通过后，虚拟机将为对象分配内存，对象所需的内存大小在类加载完成后可完全确定，为对象分配空间的任务实际上便等同于把一块确定大小的内存块从Java堆中划分出来。<p></p><ul><li>假设Java堆中内存是绝对规整的，所有被使用过的内存都被放在一边，空闲的内存被放在另一边，中间放着一个指针作为分界点的指示器，那所分配内存就仅仅是把那个指针向空闲空间方向挪动一段与对象大小相等的距离，这种分配方式称为“指针碰撞”（Bump The Pointer）。</li><li>如果Java堆中的内存并不是规整的，已被使用的内存和空闲的内存相互交错在一起，那就没有办法简单地进行指针碰撞了，虚拟机就必须维护一个列表，记录上哪些内存块是可用的，在分配的时候从列表中找到一块足够大的空间划分给对象实例，并更新列表上的记录，这种分配方式称 为“空闲列表”（Free List）。<p></p></li></ul>&nbsp; &nbsp; &nbsp; &nbsp;选择哪种分配方式由Java堆是否规整决定，而Java堆是否规整又由所采用的垃圾收集器是否带有空间压缩整理（Compact）的能力决定。因此，当使用Serial、ParNew等带压缩整理过程的收集器时，系统采用的分配算法是“指针碰撞”，既简单又高效；而当使用CMS这种基于清除（Sweep）算法的收集器时，理论上就只能采用较为复杂的“空闲列表”来分配内存（实际上，为了能在多数情况下分配得更快，设计了一个叫作Linear Allocation Buffer的分配缓冲区，通过空闲列表拿到一大块分配缓冲区之后，在它里面仍然可以使用指针碰撞方式来分配）。<p></p>&nbsp; &nbsp; &nbsp; &nbsp;使用“指针碰撞”在并发情况下不是线程安全的，可能出现正在给对象A分配内存，指针还没来得及修改，对象B又同时使用了原来的指针来分配内存的情况。解决这个问题有两种可选方案：<br><ul><li>一种是对分配内存空间的动作进行同步处理——实际上虚拟机是采用CAS加上失败重试的方式保证更新操作的原子性；</li><li>&nbsp; 另外一种是把内存分配的动作按照线程划分在不同的空间之中进行，即每个线程在Java堆中预先分配一小块内存，称为本地线程分配缓冲（Thread Local Allocation Buffer，TLAB），哪个线程要分配内存，就在哪个线程的本地缓冲区中分配，只有本地缓冲区用完了，分配新的缓存区时才需要同步锁定。虚拟机是否使用TLAB，可以通过-XX：+/-UseTLAB参数来设定。</li></ul><p><br></p></div></div></div></div></div></div></div></div></div></div></div><p><br></p>";
            dto.setHtml(html);
            dto.setMarkdown(html);
            articleService.addArticle(dto);
        }
        return "success";
    }

}
