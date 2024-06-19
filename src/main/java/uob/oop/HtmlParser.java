package uob.oop;

public class HtmlParser {
    /***
     * Extract the title of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the title if it's been found. Otherwise, return "Title not found!".
     */
    public static String getNewsTitle(String _htmlCode) {
        //TODO Task 1.1 - 5 marks
        //Input is html line thingy
        //Find the indexes starting and ending with </title>
        //Get the substring of those two indexs
        //Find the first occurence of |, if can't find, return the stripped string
        //Strip the string

        int s_title = _htmlCode.indexOf("<title>");
        int e_title = _htmlCode.indexOf("</title>");
        if (s_title == -1 || e_title == -1){
            return "Title not found!";
        }
        String st1 = _htmlCode.substring(s_title + 7, e_title); // String without titles
        int line = st1.indexOf(" |");
        // if cannot find "|" (No company name) , return the stripped Title
        if (line == -1){
            if (st1.isBlank()){
                return st1;
            }
            return st1.strip();
        }

        String st2 = st1.substring(0, line);
        st2 = st2.strip();
        if (!(st2.isEmpty())) {
            return st2;
        }
        return "Title not found!";

    }

    /***
     * Extract the content of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the content if it's been found. Otherwise, return "Content not found!".
     */
    public static String getNewsContent(String _htmlCode) {
        //TODO Task 1.2 - 5 marks
        // find start and end
        // Get a new substring
        int s_content = _htmlCode.indexOf("\"articleBody\":");
        if (!(s_content == -1)){
            String sc1 = _htmlCode.substring(s_content + 16);
            int e_content = sc1.indexOf("\",\"mainEntityOfPage\"");
            String sc2 = sc1.substring(0, e_content);
            if (!sc2.isEmpty()){
                return sc2.toLowerCase().trim();
            }
        }


        return "Content not found!";
    }


}
