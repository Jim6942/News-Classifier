package uob.oop;

public class Vector {
    private double[] doubElements;

    public Vector(double[] _elements) {
        //TODO Task 3.1 - 0.5 marks
        doubElements = _elements;

    }

    public double getElementatIndex(int _index) {
        //TODO Task 3.2 - 2 marks
        try {
            return doubElements[_index]; //you need to modify the return value DONE ALREADY
        }
        catch (Exception e){
            return -1;
        }

    }

    public void setElementatIndex(double _value, int _index) {
        //TODO Task 3.3 - 2 marks
        try{
            doubElements[_index] = _value;
        }
        catch (Exception e){
            doubElements[doubElements.length - 1] = _value;
        }
    }

    public double[] getAllElements() {
        //TODO Task 3.4 - 0.5 marks

        return doubElements; //you need to modify the return value DONE ALREADY
    }

    public int getVectorSize() {
        //TODO Task 3.5 - 0.5 marks
        return doubElements.length; //you need to modify the return value DONE ALREADY
    }

    public Vector reSize(int _size) {
        //TODO Task 3.6 - 6 marks
        if (_size == doubElements.length || _size <= 0){
            return new Vector(doubElements);
        }
        else {
            if (_size < doubElements.length) {
                double[] d = new double[_size];
                for (int i = 0; i < _size; i++) {
                    d[i] = doubElements[i];
                }
                return new Vector(d);
            } else {
                double[] d = new double[_size];
                for (int i = 0; i < doubElements.length; i++) {
                    d[i] = doubElements[i];
                }
                for (int j = doubElements.length; j < _size; j++) {
                    d[j] = -1.0;
                }
                return new Vector(d);
            }
        }
    }

    public Vector add(Vector _v) {
        //TODO Task 3.7 - 2 marks

        if (_v.getAllElements().length > doubElements.length){
            double[] addition = new double[_v.getVectorSize()];
            Vector current = new Vector(doubElements).reSize(_v.getVectorSize()); // Resize vector form of doubELements to the size of input vector
            // Vector current is resized original vector when input > original vector

            for (int i = 0; i < current.getAllElements().length; i++){
                addition[i] = current.getAllElements()[i] + _v.getAllElements()[i];
            }
            return new Vector(addition); //you need to modify the return value ALREADY DONE
        }
        else{
            double[] addition = new double[doubElements.length];
            Vector v = _v.reSize(doubElements.length);
            // v is resize Vector for _v(input)

            for (int i = 0; i < doubElements.length; i++){
                addition[i] = doubElements[i] + v.getAllElements()[i];
            }
            return new Vector(addition); //you need to modify the return value ALREADY DONE
        }


    }

    public Vector subtraction(Vector _v) {
        //TODO Task 3.8 - 2 marks
        if (_v.getAllElements().length > doubElements.length){
            double[] subs = new double[_v.getVectorSize()];
            Vector current = new Vector(doubElements).reSize(_v.getVectorSize()); // Resize vector form of doubELements to the size of input vector
            // Vector current is resized original vector when input > original vector

            for (int i = 0; i < current.getAllElements().length; i++){
                subs[i] = current.getAllElements()[i] - _v.getAllElements()[i];
            }
            return new Vector(subs); //you need to modify the return value ALREADY DONE
        }
        else{
            double[] subs = new double[doubElements.length];
            Vector v = _v.reSize(doubElements.length);
            // v is resize Vector for _v(input)

            for (int i = 0; i < doubElements.length; i++){
                subs[i] = doubElements[i] - v.getAllElements()[i];
            }
            return new Vector(subs); //you need to modify the return value ALREADY DONE
        }

    }

    public double dotProduct(Vector _v) {
        //TODO Task 3.9 - 2 marks
        if (_v.getAllElements().length > doubElements.length){
            double[] prods = new double[_v.getVectorSize()];
            Vector current = new Vector(doubElements).reSize(_v.getVectorSize()); // Resize vector form of doubELements to the size of input vector
            // Vector current is resized original vector when input > original vector

            for (int i = 0; i < current.getAllElements().length; i++){
                prods[i] = current.getAllElements()[i] * _v.getAllElements()[i];
            }
            double dotProduct = 0.0;
            for (double prod : prods){
                dotProduct += prod;
            }
            return dotProduct; //you need to modify the return value ALREADY DONE

        }
        else{
            double[] prods = new double[doubElements.length];
            Vector v = _v.reSize(doubElements.length);
            // v is resize Vector for _v(input)

            for (int i = 0; i < doubElements.length; i++){
                prods[i] = doubElements[i] * v.getAllElements()[i];
            }
            double dotProduct = 0.0;
            for (double prod : prods){
                dotProduct += prod;
            }
            return dotProduct; //you need to modify the return value ALREADY DONE

        }

    }

    public double cosineSimilarity(Vector _v) {
        //TODO Task 3.10 - 6.5 marks

        if (_v.getAllElements().length > doubElements.length){
            double[] prods = new double[_v.getVectorSize()];
            Vector current = reSize(_v.getVectorSize()); // Resize vector form of doubELements to the size of input vector
            // Vector current is resized original vector when input > original vector

            for (int i = 0; i < current.getAllElements().length; i++){
                prods[i] = current.getAllElements()[i] * _v.getAllElements()[i];
            }
            double dotProduct = 0.0;
            for (double prod : prods){
                dotProduct += prod;
            }

            return dotProduct/(Math.sqrt(current.dotProduct(current)) * Math.sqrt(_v.dotProduct(_v)));//you need to modify the return value ALREADY DONE

        }
        else{
            double[] prods = new double[doubElements.length];
            Vector v = _v.reSize(doubElements.length);
            // v is resize Vector for _v(input)

            for (int i = 0; i < doubElements.length; i++){
                prods[i] = doubElements[i] * v.getAllElements()[i];
            }
            double dotProduct = 0.0;
            for (double prod : prods) {
                dotProduct += prod;
            }
            return dotProduct/(Math.sqrt(v.dotProduct(v)) * Math.sqrt(dotProduct(this))); //you need to modify the return value ALREADY DONE

        }
    }

    @Override
    public boolean equals(Object _obj) {
        Vector v = (Vector) _obj;
        boolean boolEquals = true;

        if (this.getVectorSize() != v.getVectorSize())
            return false;

        for (int i = 0; i < this.getVectorSize(); i++) {
            if (this.getElementatIndex(i) != v.getElementatIndex(i)) {
                boolEquals = false;
                break;
            }
        }
        return boolEquals;
    }

    @Override
    public String toString() {
        StringBuilder mySB = new StringBuilder();
        for (int i = 0; i < this.getVectorSize(); i++) {
            mySB.append(String.format("%.5f", doubElements[i])).append(",");
        }
        mySB.delete(mySB.length() - 1, mySB.length());
        return mySB.toString();
    }
}
