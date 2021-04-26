package java;

/**
 * @author SunHang
 * @className: SharedBikeCompany
 * @description:
 * @createTime 2021/3/20 16:04
 */
public class SharedBikeCompany {
    public int bikeCompanyId;
    public String bikeCompanyName;
    public SharedBike[] sharedBikes;
    public int bikeSum;
    public int borrowCount;
    static final int OFO_BIKE_COUNTS = 3;
    static final int HELLO_BIKE_COUNTS = 3;
    static final int MO_BIKE_COUNTS = 3;

    public SharedBikeCompany(int bikeCompanyId, String bikeCompanyName, SharedBike[] sharedBikes, int bikeSum, int borrowCount) {
        this.bikeCompanyId = bikeCompanyId;
        this.bikeCompanyName = bikeCompanyName;
        this.sharedBikes = sharedBikes;
        this.bikeSum = bikeSum;
        this.borrowCount = borrowCount;
    }


    public void bikeCompanyBikeInfo() {
        bikeCompanyInfo();
        System.out.println("| 单车序号 | 单车品牌 | 单车状态 | 借出时间 | ");
        for(int i = 0; i<bikeSum;i++){
            System.out.println("*******************************************");
            sharedBikes[i].bikeInfo(i+1);
        }
    }

    public void bikeCompanyInfo(){
        bikeCompanyView();
        System.out.println("|    " + bikeCompanyId + "    |  " + bikeCompanyName + " |      "
                + bikeSum + "      |       " + borrowCount + "      |");
        System.out.println("******************************************************");
    }
    public void bikeCompanyView(){
        System.out.println("******************************************************");
        System.out.println("| 公司序号 |  公司名称 | 公司单车数量 | 公司单车借出次数| ");
        System.out.println("******************************************************");
    }

}
