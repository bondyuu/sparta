import java.util.*;

class Vehicle{
	String busCon;
	UUID busNum;
	String taxiCon;
	UUID taxiNum;
    int oil;
    int vel;
    int velDiff;
    int max;


    public void defBus() {//������ �ý� ���ľ���
        this.busNum = UUID.randomUUID(); //����
        this.oil = 100;
        this.vel = 0;
        
    }

    public void defTaxi() {
        this.taxiNum = UUID.randomUUID();
        this.oil = 100;
        this.vel = 0;
        
    }

    
    
}

class Bus extends Vehicle {
    int fee;
    int curPassenger;
    int totalFee;

    public void crtBus() {
        super.defBus();
        this.max = 30;
        this.busCon = "����";
        this.fee = 1000;
        this.curPassenger = 0;
        System.out.println("���� ��ȣ = " + this.busNum);
    }

    public void takePassenger(int passenger) {

        if (curPassenger + passenger > max) {
            System.out.println("Alert: �ִ� �°� �� �ʰ�");
        } else {
            this.curPassenger += passenger;
            this.max -= passenger;
            this.totalFee = 1000 * passenger;
        
	        System.out.println("ž�� �°� �� = " + passenger);
	        System.out.println("�ܿ� �°� �� = " + this.max);
	        System.out.println("��� Ȯ�� = " + this.totalFee);
        }
    
    }

    public void oilDiff(int oilSpd) {
        this.oil += oilSpd;
        if (oilSpd < 0) {//���
            System.out.println("������ = " + this.oil);
            if( this.oil < 10) {
                this.busCon = "��������";
                System.out.println("���� = " + this.busCon);
                System.out.println("Alert: ������ �ʿ��մϴ�.");
            }
        } else {
            System.out.println("���� = " + this.busCon);
            System.out.println("������ = " + this.oil);
        }
    }
    
    public void chgBuscon(String con1) {
    	this.busCon = con1;
    	this.max = 30;
    	this.curPassenger = 0;
    }
    

}




class Taxi extends Vehicle {
    int goal;
    int dist;
    int defDist;
    int defFee;
    int distFee;
    int earn;

    public void crtTaxi() {
        super.defTaxi();
        this.max = 4;
        this.taxiCon = "�Ϲ�";
        this.defFee = 3000;
        this.distFee = 1000;
        this.defDist = 1;
        
        System.out.println("�ý� ��ȣ = " + this.taxiNum);
        System.out.println("������ = " + this.oil);
        System.out.println("���� = " + this.taxiCon);
    }

    public void takePassenger2(int passenger, String destination, int distance) {

        if (passenger > max) {
            System.out.println("Alert: �ִ� �°� �� �ʰ�");
        } else {
            
            this.max -= passenger;
            this.earn += (this.defFee + (distance - this.defDist) * this.distFee);
            System.out.println("ž�� �°� �� = " + passenger);
            System.out.println("�ܿ� �°� �� = " + this.max);
            System.out.println("�⺻ ��� Ȯ�� = " + this.defFee);
            System.out.println("������ = " + destination);
            System.out.println("���������� �Ÿ� = " + distance);
            System.out.println("������ ��� = " + (this.defFee + (distance - this.defDist) * this.distFee));
            
        }
    
    }

    public int oilDiff(int oilSpd) {
        return this.oil += oilSpd;
        
    }

    public void pay() {
    	this.max = 4;
        System.out.println("������ = " + this.oil);
        if (this.oil ==0) {
            this.taxiCon = "����Ұ�";
            System.out.println("���� = " + this.taxiCon);
            System.out.println("���� ��� = " + this.earn);
            System.out.println("Alert: ������ �ʿ��մϴ�");
        } else {
        	System.out.println("���� ��� = " + this.earn);
	    }
	}
    
    public void chgTaxicon(String con2) {
    	this.taxiCon = con2;
    }

}

public class Homework {
    public static void main(String[] args) {
    	
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        Taxi taxi1 = new Taxi();
        Taxi taxi2 = new Taxi();
        
        System.out.println("���� ��ȣ Ȯ���ϱ�");
        bus1.crtBus();
        bus2.crtBus();
        
        System.out.println("\n�°� 2�� �ޱ�");
        bus1.takePassenger(2);
        
        System.out.println("\n������ 50 ����");
        bus1.oilDiff(-50);
        bus1.chgBuscon("��������");
        
        System.out.println("\n������ 10 ����");
        bus1.oilDiff(10);
        bus1.chgBuscon("������");
        
        System.out.println("\n�°� 45�� �ޱ�");
        bus1.takePassenger(45);
        
        System.out.println("\n�°� 5�� �ޱ�");
        bus1.takePassenger(5);
        
        System.out.println("\n������ 55 ����");
        bus1.oilDiff(-55);
        
        
        System.out.println("\n�ý� ��ȣ Ȯ��");
        taxi1.crtTaxi();
        taxi2.crtTaxi();
        
        System.out.println("\n�°�:2�� ������:���￪ �Ÿ�:2km");
        taxi1.takePassenger2(2, "���￪", 2);
        
        taxi1.oilDiff(-80);
        
        System.out.println("\n��� ����");
        taxi1.pay();
        
        System.out.println("\n�°� 5�� �ޱ�");
        taxi1.takePassenger2(5, "���￪", 5);
        
        System.out.println("\n�°�:3�� ������:���ε����д����� �Ÿ�:12km");
        taxi1.takePassenger2(3, "���ε����д�����", 12);
        taxi1.oilDiff(-20);
        
        System.out.println("\n��ݰ���");
        taxi1.pay();
        

    }
}
