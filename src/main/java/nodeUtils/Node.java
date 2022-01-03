package nodeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    public static final String REGEX_OPEN_TAG = "<.+?>";

    private String openTag;
    private String closeTag;
    private String content;

    /**
     * Field showing whether there is another one after the current node.
     */
    private boolean hasTrailer = false;

    /**
     * Link to next node which has same rank as a current node.
     */
    private Node trailer;

    public String getOpenTag() {
        return openTag;
    }

    public String getCloseTag() {
        return closeTag;
    }

    public String getContent() {
        return content;
    }

    public String getTagsWithoutContent() {
        return getOpenTag() + getCloseTag();
    }

    public boolean isHasTrailer() {
        return hasTrailer;
    }

    public Node getTrailer() {
        if (isHasTrailer()) {
            return trailer;
        }
        System.out.println("This node is wrapper of content! No one node here...");
        return null;
    }

    public Node(String sample) {
        this.openTag = parseOpenTag(sample);
        this.closeTag = parseCloseTag(sample);
        this.content = parseContent(sample);
    }

    /**
     * Return name of openTag from string to parse.
     *
     * @param sample string that needs to parse.
     * @return string is name of openTag.
     */
    private String parseOpenTag(String sample) {
        Pattern pattern = Pattern.compile(REGEX_OPEN_TAG);
        Matcher matcher = pattern.matcher(sample);
        if (matcher.find()) return matcher.group();
        return null;
    }

    /**
     * Return name of closeTag from string to parse.
     *
     * @param sample string that needs to parse.
     * @return string is name of closeTag.
     */
    private String parseCloseTag(String sample) {
        StringBuilder regexCloseTagBuilder = new StringBuilder();
        int logicalEndOfOpenTag = getLogicalEndOfOpenTag(this.openTag);
        if (hasSpaces(this.openTag)) {
            regexCloseTagBuilder.append("</").append(this.openTag.substring(1, logicalEndOfOpenTag)).append(">");
        } else regexCloseTagBuilder.append("</").append(this.openTag.substring(1));
        return regexCloseTagBuilder.toString();
    }

    /**
     * Return content between open and close tags.
     *
     * @param sample string that needs to parse.
     * @return string that locates between open and close tags.
     */
    private String parseContent(String sample) {
        int beginContentIndex = 0;
        int endContentIndex = 0;
        Pattern patternBegin = Pattern.compile(this.openTag);
        Matcher matcher = patternBegin.matcher(sample);
        if (matcher.find()) beginContentIndex = matcher.end();
        matcher = matcher.usePattern(Pattern.compile(this.closeTag));
        if (matcher.find()) endContentIndex = matcher.start();
        matcher = matcher.usePattern(Pattern.compile(REGEX_OPEN_TAG));
        if (matcher.find()) {
            this.trailer = new Node(sample.substring(matcher.start()));
            this.hasTrailer = true;
        }
        return sample.substring(beginContentIndex, endContentIndex);
    }

    /**
     * Return index of tagName's end.
     *
     * @param openTag string of open tag.
     * @return index of tagName's end.
     */
    private int getLogicalEndOfOpenTag(String openTag) {
        for (int i = 0; i < openTag.length(); i++) {
            if (openTag.charAt(i) == ' ') return i;
        }
        return 0;
    }

    /**
     * Check tag has some extra fields in body of tag (id, color, etc.).
     *
     * @param openTag string of open tag.
     * @return true if tag has extra options. false if tag is clear.
     */
    private boolean hasSpaces(String openTag) {
        for (int i = 0; i < openTag.length(); i++) {
            if (openTag.charAt(i) == ' ') return true;
        }
        return false;
    }
}
