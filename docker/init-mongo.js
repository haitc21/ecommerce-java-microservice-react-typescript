db = connect("mongodb://root:123456@localhost:27017/admin");

if (!db.getSiblingDB("ecommerce-product").getCollectionNames().length) {
  db = db.getSiblingDB("ecommerce-product");
  db.createCollection("product");
}

if (!db.getSiblingDB("ecommerce-order").getCollectionNames().length) {
  db = db.getSiblingDB("ecommerce-order");
  db.createCollection("order");
}

if (!db.getSiblingDB("ecommerce-inventory").getCollectionNames().length) {
  db = db.getSiblingDB("ecommerce-inventory");
  db.createCollection("inventory");
}

if (!db.getSiblingDB("ecommerce-product").getCollectionNames().length) {
    db = db.getSiblingDB("ecommerce-product");
    db.createCollection("product");
  }
