package model;

public class Service {

    private int service_id;
    private String service_name;
    private boolean is_bhyt;
    private String description;
    private CategoryServices category_service;
    private int category_service_id;
    private Deparment department;
    private double fee;
    private double discount;
    private int payment_type_id;
    private int department_id;
    private String img;

    public Service(int service_id, String service_name, boolean is_bhyt, String description, CategoryServices category_service, Deparment department, double fee, double discount, int payment_type_id, String img) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.is_bhyt = is_bhyt;
        this.description = description;
        this.category_service = category_service;
        this.category_service_id = category_service_id;
        this.department = department;
        this.fee = fee;
        this.discount = discount;
        this.payment_type_id = payment_type_id;
        this.department_id = department_id;
        this.img = img;
    }
    
 
    

    public Service(int service_id, String service_name, boolean is_bhyt, String description, int category_service_id, int department_id, double fee, double discount, int payment_type_id) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.is_bhyt = is_bhyt;
        this.description = description;
        this.category_service_id = category_service_id;
        this.department_id = department_id;
        this.fee = fee;
        this.discount = discount;
        this.payment_type_id = payment_type_id;
    }

    public int getCategory_service_id() {
        return category_service_id;
    }

    public void setCategory_service_id(int category_service_id) {
        this.category_service_id = category_service_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
    
    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public boolean isIs_bhyt() {
        return is_bhyt;
    }

    public void setIs_bhyt(boolean is_bhyt) {
        this.is_bhyt = is_bhyt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryServices getCategory_service() {
        return category_service;
    }

    public void setCategory_service(CategoryServices category_service) {
        this.category_service = category_service;
    }

    public Deparment getDepartment() {
        return department;
    }

    public void setDepartment(Deparment department) {
        this.department = department;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getPayment_type_id() {
        return payment_type_id;
    }

    // Constructor
    public void setPayment_type_id(int payment_type_id) {
        this.payment_type_id = payment_type_id;
    }

       public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public Service(int service_id, String service_name, boolean is_bhyt, String description, CategoryServices category_service, Deparment department, double fee, double discount, int payment_type_id) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.is_bhyt = is_bhyt;
        this.description = description;
        this.category_service = category_service;
        this.department = department;
        this.fee = fee;
        this.discount = discount;
        this.payment_type_id = payment_type_id;
    }

   
}
