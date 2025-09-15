package loipt.example.trans_money;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> EN_TO_VI = new HashMap<>();

    static {
        EN_TO_VI.put("hello", "xin chào");
        EN_TO_VI.put("goodbye", "tạm biệt");
        EN_TO_VI.put("book", "quyển sách");
        EN_TO_VI.put("computer", "máy tính");
        EN_TO_VI.put("money", "tiền");
        EN_TO_VI.put("friend", "bạn bè");
        EN_TO_VI.put("love", "tình yêu");
        EN_TO_VI.put("school", "trường học");
        EN_TO_VI.put("teacher", "giáo viên");
        EN_TO_VI.put("student", "học sinh");
    }

    @GetMapping({"/", "/dictionary"})
    public String showForm(@RequestParam(value = "word", required = false) String word,
                           Model model) {
        String meaning = null;
        String message = null;

        if (word != null && !word.trim().isEmpty()) {
            String key = word.toLowerCase(Locale.ROOT).trim();
            meaning = EN_TO_VI.get(key);
            if (meaning == null) {
                message = "Không tìm thấy từ: '" + word + "'";
            }
        }

        model.addAttribute("word", word == null ? "" : word);
        model.addAttribute("meaning", meaning);
        model.addAttribute("message", message);
        return "dictionary";
    }
}


