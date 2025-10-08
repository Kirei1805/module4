package example.blog1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 200, message = "Tiêu đề phải có từ 5 đến 200 ký tự")
    @Column(name = "title", nullable = false)
    private String title;
    
    @NotBlank(message = "Nội dung không được để trống")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column(name = "summary", length = 500)
    private String summary;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        // Tự động tạo summary từ content nếu chưa có
        if (summary == null || summary.trim().isEmpty()) {
            summary = generateSummary(content);
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    private String generateSummary(String content) {
        if (content == null || content.trim().isEmpty()) {
            return "";
        }
        // Lấy 200 ký tự đầu tiên làm summary
        String cleanContent = content.replaceAll("<[^>]*>", "").trim();
        return cleanContent.length() > 200 ? cleanContent.substring(0, 200) + "..." : cleanContent;
    }
}