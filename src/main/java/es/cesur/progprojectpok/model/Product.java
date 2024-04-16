package es.cesur.progprojectpok.model;

/**
 * Clase que representa un producto en el sistema.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private boolean storable;
    private Integer maxStock;
    private Double price;

    /**
     * Constructor de la clase Product.
     *
     * @param id El identificador único del producto.
     * @param name El nombre del producto.
     * @param description La descripción del producto.
     * @param storable Indica si el producto es almacenable.
     * @param maxStock La cantidad máxima de stock para el producto.
     * @param price El precio del producto.
     */
    public Product(int id, String name, String description, boolean storable, Integer maxStock, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.storable = storable;
        this.maxStock = maxStock;
        this.price = price;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStorable() {
        return storable;
    }

    public void setStorable(boolean storable) {
        this.storable = storable;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", storable=" + storable +
                ", maxStock=" + maxStock +
                ", price=" + price +
                '}';
    }
}

