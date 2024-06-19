Introduction
-

This project is a simpler version of the advanced news classifier. It classifies news articles into distinct categories based on their content without using advanced AI techniques. The classifier is built using Term Frequency-Inverse Document Frequency (TF-IDF) embedding to assess the semantic closeness of the news articles.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Overview
-

The project involves several key components:

HTML Parser: Extracts news titles and content from HTML files.

NLP Processor: Cleans and processes the text data.

Vector Operations: Handles vector mathematics required for the TF-IDF computation.

News Classifier: Loads data, preprocesses it, computes TF-IDF, builds vocabulary, and assesses semantic similarity between news articles.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Components and Tasks
-

1. HTML Parser (HtmlParser.java)

Task 1.1: getNewsTitle(String _htmlCode) - Extracts the news title from the provided HTML code.

Task 1.2: getNewsContent(String _htmlCode) - Extracts the news content from the provided HTML code and converts all characters to lowercase.
#

2. NLP Processor (NLP.java)

Task 2.1: textCleaning(String _content) - Cleans the text content by removing unnecessary characters and formatting.

Task 2.2: textLemmatization(String _content) - Lemmatizes the text content to reduce words to their base forms.

Task 2.3: removeStopWords(String _content, String[] _stopWords) - Removes specified stop words from the text content.
#

3. Vector Operations (Vector.java)

Task 3.1: Vector(double[] _elements) - Constructor for initializing the vector with elements.

Task 3.2: getElementatIndex(int _index) - Retrieves the element at the specified index.

Task 3.3: setElementatIndex(double _value, int _index) - Sets the element at the specified index to a given value.

Task 3.4: getAllElements() - Returns all elements of the vector.

Task 3.5: getVectorSize() - Returns the size of the vector.

Task 3.6: reSize(int _size) - Resizes the vector to the specified size.

Task 3.7: add(Vector _v) - Adds another vector to the current vector.

Task 3.8: subtraction(Vector _v) - Subtracts another vector from the current vector.

Task 3.9: dotProduct(Vector _v) - Computes the dot product with another vector.

Task 3.10: cosineSimilarity(Vector _v) - Computes the cosine similarity with another vector.
#

4. News Classifier (NewsClassifier.java)

Task 4.1: loadData() - Loads the news articles data.

Task 4.2: preProcessing() - Preprocesses the news articles by cleaning and preparing the text data.

Task 4.3: calculateTFIDF(String[] _cleanedContents) - Computes the TF-IDF values for the cleaned contents.

Task 4.4: buildVocabulary(String[] _cleanedContents) - Builds the vocabulary from the cleaned contents.

Task 4.5: newsSimilarity(int _newsIndex) - Assesses the semantic similarity of the news articles.

Task 4.6: groupingResults(String _firstTitle, String _secondTitle) - Groups the news articles based on their semantic similarity.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Expected output
-

Two groups will be displayed and news articles within those groups will be similar. In the provided files, the twenty news articles are about Bitcoin and NASA respectively. This algorithm will differentiate those articles put them into different groups. The final output should be 10 Bitcoin news in one group and 10 NASA news in another group.
