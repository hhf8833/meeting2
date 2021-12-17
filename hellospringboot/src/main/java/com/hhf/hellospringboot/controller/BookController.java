package com.hhf.hellospringboot.controller;

import com.hhf.hellospringboot.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HP
 * 控制器对书架上进行添加、查找以及查看
 * `@RestController` 将返回的对象数据直接以 JSON 或 XML 形式写入 HTTP 响应(Response)中。**
 * 1. `@RestController`  **将返回的对象数据直接以 JSON 或 XML 形式写入 HTTP 响应(Response)中。**绝大部分情况下都是直接以  JSON 形式返回给客户端，很少的情况下才会以 XML 形式返回。转换成 XML 形式还需要额为的工作，上面代码中演示的直接就是将对象数据直接以 JSON 形式写入 HTTP 响应(Response)中。关于`@Controller`和`@RestController` 的对比，我会在下一篇文章中单独介绍到（`@Controller` +`@ResponseBody`= `@RestController`）。
 * 2. `@RequestMapping` :上面的示例中没有指定 GET 与 PUT、POST 等，因为**`@RequestMapping`默认映射所有HTTP Action**，你可以使用`@RequestMapping(method=ActionType)`来缩小这个映射。
 * 3. `@PostMapping`实际上就等价于 `@RequestMapping(method = RequestMethod.POST)`，同样的 ` @DeleteMapping` ,`@GetMapping`也都一样，常用的 HTTP Action 都有一个这种形式的注解所对应。
 * 4. `@PathVariable` :取url地址中的参数。`@RequestParam ` url的查询参数值。
 * 5. `@RequestBody`:可以**将 *HttpRequest* body 中的 JSON 类型数据反序列化为合适的 Java 类型。**
 * 6. `ResponseEntity`: **表示整个HTTP Response：状态码，标头和正文内容**。我们可以使用它来自定义HTTP Response 的内容。
 */
@RestController
@RequestMapping("/api")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @PostMapping("/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) {
        books.remove(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}
