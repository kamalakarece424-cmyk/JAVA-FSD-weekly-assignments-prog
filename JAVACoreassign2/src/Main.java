
	interface Payment {
	    void payAmount();
	}
	class CreditCardPayment implements Payment {

	    @Override
	    public void payAmount() {
	        System.out.println("Payment made using Credit Card");
	    }
	}
	class DebitCardPayment implements Payment {

	    @Override
	    public void payAmount() {
	        System.out.println("Payment made using Debit Card");
	    }
	}
	public class Main {

	    public static void main(String[] args) {

	        Payment payment;

	        payment = new CreditCardPayment();
	        payment.payAmount();

	        payment = new DebitCardPayment();
	        payment.payAmount();
	    }
	}

	
