package test;

class A {
	int a;
	int b;
	A(){}
	A(int a,int b){
		this.a=a;
		this.b=b;
	}
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
}

public class Test {
	public static void main(String[] args) {
//		A a=new A();
//		a.setA(10);
//		a.setB(20);
		A a=new A(10,20);
		System.out.println(a.getA());
		System.out.println(a.getB());
	}
}
