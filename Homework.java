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


    public void defBus() {//버스와 택시 합쳐야함
        this.busNum = UUID.randomUUID(); //수정
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
        this.busCon = "운행";
        this.fee = 1000;
        this.curPassenger = 0;
        System.out.println("버스 번호 = " + this.busNum);
    }

    public void takePassenger(int passenger) {

        if (curPassenger + passenger > max) {
            System.out.println("Alert: 최대 승객 수 초과");
        } else {
            this.curPassenger += passenger;
            this.max -= passenger;
            this.totalFee = 1000 * passenger;
        
	        System.out.println("탑승 승객 수 = " + passenger);
	        System.out.println("잔여 승객 수 = " + this.max);
	        System.out.println("요금 확인 = " + this.totalFee);
        }
    
    }

    public void oilDiff(int oilSpd) {
        this.oil += oilSpd;
        if (oilSpd < 0) {//사용
            System.out.println("주유량 = " + this.oil);
            if( this.oil < 10) {
                this.busCon = "차고지행";
                System.out.println("상태 = " + this.busCon);
                System.out.println("Alert: 주유가 필요합니다.");
            }
        } else {
            System.out.println("상태 = " + this.busCon);
            System.out.println("주유량 = " + this.oil);
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
        this.taxiCon = "일반";
        this.defFee = 3000;
        this.distFee = 1000;
        this.defDist = 1;
        
        System.out.println("택시 번호 = " + this.taxiNum);
        System.out.println("주유량 = " + this.oil);
        System.out.println("상태 = " + this.taxiCon);
    }

    public void takePassenger2(int passenger, String destination, int distance) {

        if (passenger > max) {
            System.out.println("Alert: 최대 승객 수 초과");
        } else {
            
            this.max -= passenger;
            this.earn += (this.defFee + (distance - this.defDist) * this.distFee);
            System.out.println("탑승 승객 수 = " + passenger);
            System.out.println("잔여 승객 수 = " + this.max);
            System.out.println("기본 요금 확인 = " + this.defFee);
            System.out.println("목적지 = " + destination);
            System.out.println("목적지까지 거리 = " + distance);
            System.out.println("지불할 요금 = " + (this.defFee + (distance - this.defDist) * this.distFee));
            
        }
    
    }

    public int oilDiff(int oilSpd) {
        return this.oil += oilSpd;
        
    }

    public void pay() {
    	this.max = 4;
        System.out.println("주유량 = " + this.oil);
        if (this.oil ==0) {
            this.taxiCon = "운행불가";
            System.out.println("상태 = " + this.taxiCon);
            System.out.println("누적 요금 = " + this.earn);
            System.out.println("Alert: 주유가 필요합니다");
        } else {
        	System.out.println("누적 요금 = " + this.earn);
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
        
        System.out.println("버스 번호 확인하기");
        bus1.crtBus();
        bus2.crtBus();
        
        System.out.println("\n승객 2명 받기");
        bus1.takePassenger(2);
        
        System.out.println("\n주유량 50 감소");
        bus1.oilDiff(-50);
        bus1.chgBuscon("차고지행");
        
        System.out.println("\n주유량 10 증가");
        bus1.oilDiff(10);
        bus1.chgBuscon("운행중");
        
        System.out.println("\n승객 45명 받기");
        bus1.takePassenger(45);
        
        System.out.println("\n승객 5명 받기");
        bus1.takePassenger(5);
        
        System.out.println("\n주유량 55 감소");
        bus1.oilDiff(-55);
        
        
        System.out.println("\n택시 번호 확인");
        taxi1.crtTaxi();
        taxi2.crtTaxi();
        
        System.out.println("\n승객:2명 목적지:서울역 거리:2km");
        taxi1.takePassenger2(2, "서울역", 2);
        
        taxi1.oilDiff(-80);
        
        System.out.println("\n요금 결제");
        taxi1.pay();
        
        System.out.println("\n승객 5명 받기");
        taxi1.takePassenger2(5, "서울역", 5);
        
        System.out.println("\n승객:3명 목적지:구로디지털단지역 거리:12km");
        taxi1.takePassenger2(3, "구로디지털단지역", 12);
        taxi1.oilDiff(-20);
        
        System.out.println("\n요금결제");
        taxi1.pay();
        

    }
}
