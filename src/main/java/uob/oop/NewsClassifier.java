package uob.oop;

import java.text.DecimalFormat;

public class NewsClassifier {
    public String[] myHTMLs;
    public String[] myStopWords = new String[127];
    public String[] newsTitles;
    public String[] newsContents;
    public String[] newsCleanedContent;
    public double[][] newsTFIDF;

    private final String TITLE_GROUP1 = "Osiris-Rex's sample from asteroid Bennu will reveal secrets of our solar system";
    private final String TITLE_GROUP2 = "Bitcoin slides to five-month low amid wider sell-off";

    public Toolkit myTK;

    public NewsClassifier() {
        myTK = new Toolkit();
        myHTMLs = myTK.loadHTML();
        myStopWords = myTK.loadStopWords();

        loadData();
    }

    public static void main(String[] args) {
        NewsClassifier myNewsClassifier = new NewsClassifier();

        myNewsClassifier.newsCleanedContent = myNewsClassifier.preProcessing();

        myNewsClassifier.newsTFIDF = myNewsClassifier.calculateTFIDF(myNewsClassifier.newsCleanedContent);

        //Change the _index value to calculate similar based on a different news article.
        double[][] doubSimilarity = myNewsClassifier.newsSimilarity(0);

        System.out.println(myNewsClassifier.resultString(doubSimilarity, 10));

        String strGroupingResults = myNewsClassifier.groupingResults(myNewsClassifier.TITLE_GROUP1, myNewsClassifier.TITLE_GROUP2);
        System.out.println(strGroupingResults);
    }

    public void loadData() {
        //TODO 4.1 - 2 marks
        newsTitles = new String[myHTMLs.length];
        newsContents = new String[myHTMLs.length];
        for (int i = 0; i < myHTMLs.length; i++){
            newsTitles[i] = HtmlParser.getNewsTitle(myHTMLs[i]);
            newsContents[i] = HtmlParser.getNewsContent(myHTMLs[i]);
        }

    }

    public String[] preProcessing() {
        String[] myCleanedContent = null;
        //TODO 4.2 - 5 marks
        myCleanedContent = new String[newsContents.length];
        for (int i = 0; i < newsContents.length; i++){
            String c = NLP.textCleaning(newsContents[i]);      // c == cleaned
            String cl = NLP.textLemmatization(c);              // cl == cleaned and lemmatized
            String clr = NLP.removeStopWords(cl, myStopWords); // clr == cleaned, lemmatized and removed
            myCleanedContent[i] = clr;
        }

        return myCleanedContent;
    }

    public double[][] calculateTFIDF(String[] _cleanedContents) {
        String[] vocabularyList = buildVocabulary(_cleanedContents);
        double[][] myTFIDF = null;

        //TODO 4.3 - 10 marks
        /*
        _cleanedContents[i] - D (Document)
        vocabularyList - W
         */
        myTFIDF = new double[_cleanedContents.length][vocabularyList.length];


        int t_documents = _cleanedContents.length;
        for (int i = 0; i < t_documents; i++){
            for (int j = 0; j < vocabularyList.length; j++){
                Double tf = find_tf(vocabularyList[j], _cleanedContents[i].split(" "));
                Double idf = find_idf(vocabularyList[j], _cleanedContents);
                myTFIDF[i][j] = tf * idf;
            }
        }




        return myTFIDF;
    }

    public String[] buildVocabulary(String[] _cleanedContents) {
        String[] arrayVocabulary = null;
        //TODO 4.4 - 10 marks

        StringBuilder total_words = new StringBuilder();
        for (String s : _cleanedContents){
            total_words.append(s).append(" ");
        }

        String[] all_words = total_words.toString().split(" ");
        String[] arrayVocabulary_untrimmed = new String[all_words.length];
        int k = 0;


        for (int i = 0; i < all_words.length; i++){
            String w = all_words[i];
            if (!is_in(w, arrayVocabulary_untrimmed)){
                arrayVocabulary_untrimmed[k] = w;
                k++;
            }
        }

        arrayVocabulary = new String[k];
        System.arraycopy(arrayVocabulary_untrimmed, 0, arrayVocabulary, 0, k);

        return arrayVocabulary;
    }

    public double[][] newsSimilarity(int _newsIndex) {
        double[][] mySimilarity = null;
        //TODO 4.5 - 15 marks
        int content_len = newsTFIDF.length;
        mySimilarity = new double[content_len][2];
        Vector c = new Vector(newsTFIDF[_newsIndex]);



        for (int i = 0; i < content_len; i++){
            mySimilarity[i][0] = i;
            mySimilarity[i][1] = c.cosineSimilarity(new Vector(newsTFIDF[i]));

        }

        james_sort(mySimilarity, 1);

        return mySimilarity;
    }

    public String groupingResults(String _firstTitle, String _secondTitle) {
        int[] arrayGroup1 = null, arrayGroup2 = null;

        //TODO 4.6 - 15 marks
        int f_index = search_index(_firstTitle, newsTitles);
        int s_index = search_index(_secondTitle, newsTitles);
        double[][] f_similarity = james_sort(newsSimilarity(f_index), 0);
        double[][] s_similarity = james_sort(newsSimilarity(s_index), 0);
        int news_len = f_similarity.length;
        int[] unsized_arrayGroup1 = new int[news_len];
        int count1 = 0;
        int[] unsized_arrayGroup2 = new int[news_len];
        int count2 = 0;

        for (int i = 0; i < news_len; i++){
            if (f_similarity[i][1] > s_similarity[i][1]){
                unsized_arrayGroup1[count1] = (int) f_similarity[i][0];
                ++count1;
            }
            else{
                unsized_arrayGroup2[count2] = (int) f_similarity[i][0];
                ++count2;
            }
        }
        arrayGroup1 = new int[count1];
        arrayGroup2 = new int[count2];
        System.arraycopy(unsized_arrayGroup1, 0, arrayGroup1, 0,count1);
        System.arraycopy(unsized_arrayGroup2, 0, arrayGroup2, 0,count2);


        return resultString(arrayGroup1, arrayGroup2);
    }

    public String resultString(double[][] _similarityArray, int _groupNumber) {
        StringBuilder mySB = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        for (int j = 0; j < _groupNumber; j++) {
            for (int k = 0; k < _similarityArray[j].length; k++) {
                if (k == 0) {
                    mySB.append((int) _similarityArray[j][k]).append(" ");
                } else {
                    String formattedCS = decimalFormat.format(_similarityArray[j][k]);
                    mySB.append(formattedCS).append(" ");
                }
            }
            mySB.append(newsTitles[(int) _similarityArray[j][0]]).append("\r\n");
        }
        mySB.delete(mySB.length() - 2, mySB.length());
        return mySB.toString();
    }

    public String resultString(int[] _firstGroup, int[] _secondGroup) {
        StringBuilder mySB = new StringBuilder();
        mySB.append("There are ").append(_firstGroup.length).append(" news in Group 1, and ").append(_secondGroup.length).append(" in Group 2.\r\n").append("=====Group 1=====\r\n");

        for (int i : _firstGroup) {
            mySB.append("[").append(i + 1).append("] - ").append(newsTitles[i]).append("\r\n");
        }
        mySB.append("=====Group 2=====\r\n");
        for (int i : _secondGroup) {
            mySB.append("[").append(i + 1).append("] - ").append(newsTitles[i]).append("\r\n");
        }

        mySB.delete(mySB.length() - 2, mySB.length());
        return mySB.toString();
    }

    // The following two methods are my methods

    boolean is_in(String w, String[] words){            // check whether word is in array
        for (String word : words){
            if (w.equals(word)){
                return true;
            }
        }
        return false;
    }

    int search_index(String w,String[] words){  // Return the index of a string in a String[] array
        for (int i = 0; i < words.length; i++){
            if (w.equals(words[i])){
                return i;
            }
        }
        return -1;
    }

    /*
    int find_max_len(String[] s){                         // This finds the max length of cleaned contents.split()
        int len = 0;
        for (int i = 0; i < s.length; i++){
            if (s[i].split(" ").length > len){
                len = s[i].split(" ").length;
            }
        }
        return len;
    }


     */
    double find_tf(String t, String[] d){
        int times = 0;
        for (int i = 0; i < d.length; i++){
            if (t.equals(d[i])){
                times++;
            }
        }
        return (double) times /d.length;
    }

    double find_idf(String t, String[] corpus){
        int count = 0;
        for(int i = 0; i < corpus.length; i++){
            if (is_in(t, corpus[i].split(" "))){
                count++;
            }
        }
        return Math.log((double) corpus.length/count) + 1;
    }

    // This takes array to sort and the index(1.Number and 2.CS value) of 2nd array)

    /*
    // MY VARIATION OF BUBBLE SORT
    double[][] james_sort(double[][] to_sort, int k){
        double temp;
        if (k == 1){
            for (int i = 0; i < to_sort.length; i++){
                for (int j = i; j < to_sort.length; j++){
                    if (to_sort[i][k] < to_sort[j][k]){
                        temp = to_sort[i][1];
                        to_sort[i][1] = to_sort[j][1];
                        to_sort[j][1] = temp;

                        temp = to_sort[i][0];
                        to_sort[i][0] = to_sort[j][0];
                        to_sort[j][0] = temp;
                    }
                }
            }
        }
        else if (k == 0){
            for (int i = 0; i < to_sort.length; i++){
                for (int j = i; j < to_sort.length; j++){
                    if (to_sort[i][k] > to_sort[j][k]){
                        temp = to_sort[i][1];
                        to_sort[i][1] = to_sort[j][1];
                        to_sort[j][1] = temp;

                        temp = to_sort[i][0];
                        to_sort[i][0] = to_sort[j][0];
                        to_sort[j][0] = temp;
                    }
                }
            }
        }

        return to_sort;

    }
    */

    // MY VARIATION OF QUICK SORT
    double[][] james_sort(double[][] to_sort, int mode) {
        int len = to_sort.length;
        james_quick_sort(to_sort, 0, len - 1, mode);
        return to_sort;
    }

    void james_quick_sort(double[][] arr, int low, int high, int mode) {
        if (low < high) {
            int partitionIndex = james_partition(arr, low, high, mode);

            james_quick_sort(arr, low, partitionIndex - 1, mode);
            james_quick_sort(arr, partitionIndex + 1, high, mode);
        }
    }

    int james_partition(double[][] arr, int low, int high, int mode) {
        double p = arr[high][mode];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((mode == 0 && arr[j][0] <= p) || (mode == 1 && arr[j][1] >= p)) {
                i++;

                double[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        double[] temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


}

