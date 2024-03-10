package ComplexNumbers;

public class NumarComplex {

    float re;
    float im;


    public NumarComplex(float re, float im) {
        this.re = re;
        this.im = im;
    }


    public float getRe() {
        return re;
    }

    public void setRe(float re) {
        this.re = re;
    }

    public float getIm() {
        return im;
    }

    public void setIm(float im) {
        this.im = im;
    }

    NumarComplex adunare(NumarComplex a, NumarComplex b){
        float real = a.getRe() + b.getRe();
        float imag = a.getIm() + b.getIm();

        return new NumarComplex(real, imag);
    }

    NumarComplex scadere(NumarComplex a, NumarComplex b){
        float real = a.getRe() - b.getRe();
        float imag = a.getIm() - b.getIm();

        return new NumarComplex(real, imag);
    }

    NumarComplex inmultire(NumarComplex a, NumarComplex b){
        float real = a.getRe() * b.getRe() - a.getIm() * b.getIm();
        float imag = a.getRe() * b.getIm() + a.getIm() * b.getRe();

        return new NumarComplex(real, imag);
    }

    NumarComplex impartire(NumarComplex a, NumarComplex b){
        NumarComplex c = inmultire(a, conjugat(b));
        float real = c.getRe() / (b.getRe()* b.getRe() - b.getIm()*b.getIm());
        float imag = c.getRe() / (b.getRe()* b.getRe() - b.getIm()*b.getIm());

        return new NumarComplex(real, imag);
    }

    NumarComplex conjugat(NumarComplex a){
        return new NumarComplex(a.getRe(), -a.getIm());
    }
}
