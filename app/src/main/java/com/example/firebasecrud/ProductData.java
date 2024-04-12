
package com.example.firebasecrud;

public class ProductData {


    private String id;
    private String pname;

    private String pprice;

    private String pdes;

    private String pimg;

    public ProductData()
    {
    }

    public ProductData(String id, String pname, String pprice, String pdes,String pimg) {
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
        this.pdes = pdes;
        this.pimg = pimg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPdes() {
        return pdes;
    }

    public void setPdes(String pdes) {
        this.pdes = pdes;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    @Override
    public String toString() {
        return "View_Productdata{" +
                "id='" + id + '\'' +
                ", pname='" + pname + '\'' +
                ", pprice='" + pprice + '\'' +
                ", pdes='" + pdes + '\'' +
                ", pimg='" + pimg + '\'' +
                '}';
    }
}
