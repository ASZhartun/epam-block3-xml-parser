import textReader.TextReader;

/**
 * 2. Дана строка, содержащая следующий текст (xml-документ): src/main/resources/sample.xml
 * Напишите анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип
 * (открывающий тег, закрывающий тег, содержимое тега, тег без тела).
 * Пользоваться готовыми парсерами XML для решения данной задачи нельзя.
 */

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello, XML!");
        TextReader textReader = new TextReader("src/main/resources/sample.xml");
        String xml = textReader.getText();
        System.out.println(xml);
    }
}
