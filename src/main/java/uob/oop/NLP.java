package uob.oop;

public class NLP {
    /***
     * Clean the given (_content) text by removing all the characters that are not 'a'-'z', '0'-'9' and white space.
     * @param _content Text that need to be cleaned.
     * @return The cleaned text.
     */
    public static String textCleaning(String _content) {
        StringBuilder sbContent = new StringBuilder();
        //TODO Task 2.1 - 3 marks
        //sbContent.append(_content);
        //return sbContent.toString().trim().replaceAll("[^\\w\\s]", "").toLowerCase();
        //Version 2.0
        String s = _content.replaceAll("[^\\w\\s]", "").toLowerCase();
        sbContent.append(s);
        return sbContent.toString().trim();
    }

    /***
     * Text lemmatization. Delete 'ing', 'ed', 'es' and 's' from the end of the word.
     * @param _content Text that need to be lemmatized.
     * @return Lemmatized text.
     */
    public static String textLemmatization(String _content) {
        StringBuilder sbContent = new StringBuilder();
        //TODO Task 2.2 - 3 marks
        String[] l1 = _content.split(" ");
        for (int i = 0; i < l1.length; i++){
            if (l1[i].endsWith("ing")){
                String s = l1[i].replaceAll("ing$", "");
                sbContent.append(s);
            }
            else if (l1[i].endsWith("ed")){
                String s = l1[i].replaceAll("ed$", "");
                sbContent.append(s);
            }
            else if (l1[i].endsWith("es")){
                String s = l1[i].replaceAll("es$", "");
                sbContent.append(s);
            }
            else if (l1[i].endsWith("s")){
                String s = l1[i].replaceAll("s$", "");
                sbContent.append(s);
            }
            else{
                sbContent.append(l1[i]);
            }
            sbContent.append(" ");
        }
        return sbContent.toString().trim();
    }

    /***
     * Remove stop-words from the text.
     * @param _content The original text.
     * @param _stopWords An array that contains stop-words.
     * @return Modified text.
     */
    public static String removeStopWords(String _content, String[] _stopWords) {
        StringBuilder sbConent = new StringBuilder();
        //TODO Task 2.3 - 3 marks
        // Split the content
        // Make a two dimensional array, and for each word in content, check for stop words and remove them

             String[] s = _content.split(" ");

        for (int i = 0; i < s.length; i++){
            int match = 0;
            for (int j = 0; j < _stopWords.length; j++){
                if (s[i].equals(_stopWords[j])){
                    match++;
                    break;
                }
            }
            if (match == 0){
                sbConent.append(s[i]);
                sbConent.append(" ");
            }
        }


        return sbConent.toString().trim();
    }

}
