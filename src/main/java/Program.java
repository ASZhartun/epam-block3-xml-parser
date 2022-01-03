import nodeUtils.Node;
import textReader.TextReader;


/**
 * 2. Дана строка, содержащая следующий текст (xml-документ): src/main/resources/sample.xml
 * Напишите анализатор, позволяющий последовательно возвращать содержимое узлов xml-документа и его тип
 * (открывающий тег, закрывающий тег, содержимое тега, тег без тела).
 * Пользоваться готовыми парсерами XML для решения данной задачи нельзя.
 */

public class Program {
    public static final String REGEX_OPEN_TAG = "<.+?>";
    public static final String REGEX_CLOSE_TAG = "<\\/.+?>";

    public static void main(String[] args) {
        System.out.println("Hello, XML!");
        TextReader textReader = new TextReader("src/main/resources/sample.xml");
        String xml = textReader.getText();
        Node nodeNotes = new Node(xml);                         // notes
        Node nodeNoteId1 = new Node(nodeNotes.getContent());    // note with id = 1
        Node nodeNoteId2 = nodeNoteId1.getTrailer();            // note with id = 2
        System.out.println(nodeNoteId1.getContent());
        System.out.println();
        System.out.println(nodeNoteId2.getContent());
        // Nodes of note with id = 1
        Node to1 = new Node(nodeNoteId1.getContent());
        Node from1 = to1.getTrailer();
        Node heading1 = from1.getTrailer();
        Node body1 = heading1.getTrailer();
        System.out.println(body1.getTagsWithoutContent());
        System.out.println(body1.getContent());

        // Node of note with id = 2
        Node to2 = new Node(nodeNoteId2.getContent());
        Node from2 = to2.getTrailer();
        Node heading2 = from2.getTrailer();
        System.out.println(heading2.getTagsWithoutContent());
        System.out.println(heading2.getContent());

    }
}
