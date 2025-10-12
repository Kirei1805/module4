package example.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {

    private final AtomicInteger visitorCount = new AtomicInteger(0);

    @After("execution(* example.book.service.BookService.borrowBook(..)) || " +
            "execution(* example.book.service.BookService.returnBook(..))")
    public void logBookChange(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        System.out.println("[LOG] " + LocalDateTime.now()
                + " - Hành động: " + method
                + " - Thay đổi trạng thái sách thành công.");
    }

    @Before("execution(* example.book.controller.*.*(..))")
    public void countVisitor(JoinPoint joinPoint) {
        int count = visitorCount.incrementAndGet();
        System.out.println("[VISITOR] Tổng lượt truy cập: " + count +
                " | Truy cập vào: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* example.book.service.*.*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        System.out.println("[ERROR] " + LocalDateTime.now()
                + " - Phương thức: " + joinPoint.getSignature().getName()
                + " - Lỗi: " + ex.getMessage());
    }
}
