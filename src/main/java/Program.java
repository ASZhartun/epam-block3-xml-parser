import textReader.TextReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2. Дана строка, содержащая следующий текст (xml-документ): src/main/resources/sample.xml
 * Напишите анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип
 * (открывающий тег, закрывающий тег, содержимое тега, тег без тела).
 * Пользоваться готовыми парсерами XML для решения данной задачи нельзя.
 */

public class Program {
    public static final String REGEX_TAG = "<.+?>";

    public static void main(String[] args) {
        System.out.println("Hello, XML!");
        TextReader textReader = new TextReader("src/main/resources/sample.xml");
        String xml = textReader.getText();
//        System.out.println(xml);
        Pattern pattern = Pattern.compile(REGEX_TAG);
        Matcher matcher = pattern.matcher(xml);
        String openTagName = "<openTagName>";
        String closeTagName = "<closeTagName>";
        String tagContent = "something wrong";
        if (matcher.find()) {
            openTagName = matcher.group();
        }
        xml = xml.substring(openTagName.length());
        pattern = Pattern.compile(openTagName.substring(1, openTagName.length() - 1));
        matcher = pattern.matcher(xml);
        if (matcher.find()) {
            closeTagName = "</" + matcher.group() + ">";
            tagContent = xml.substring(0, xml.length() - closeTagName.length() - 3);
        }
        System.out.println(openTagName);
        System.out.println();
        System.out.println(tagContent);
        System.out.println();
        System.out.println(closeTagName);
    }
}
