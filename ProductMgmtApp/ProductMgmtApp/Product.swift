//
//  Product.swift

import Foundation

struct Product: Codable {
    var productId: String
    var name: String
    var dateSupplied: String
    var quantityInStock: Int
    var unitPrice: Double

    // 1) Default initializer
    init() {
        self.productId = ""
        self.name = ""
        self.dateSupplied = ""
        self.quantityInStock = 0
        self.unitPrice = 0.0
    }

    // 2) Full initializer
    init(
        productId: String,
        name: String,
        dateSupplied: String,
        quantityInStock: Int,
        unitPrice: Double
    ) {
        self.productId = productId
        self.name = name
        self.dateSupplied = dateSupplied
        self.quantityInStock = quantityInStock
        self.unitPrice = unitPrice
    }

    // 3) Partial initializer
    init(productId: String, name: String, unitPrice: Double) {
        self.productId = productId
        self.name = name
        self.dateSupplied = ""
        self.quantityInStock = 0
        self.unitPrice = unitPrice
    }

    // Getter methods
    func getProductId() -> String {
        productId
    }

    func getName() -> String {
        name
    }

    func getDateSupplied() -> String {
        dateSupplied
    }

    func getQuantityInStock() -> Int {
        quantityInStock
    }

    func getUnitPrice() -> Double {
        unitPrice
    }

    // Setter methods
    mutating func setProductId(_ productId: String) {
        self.productId = productId
    }

    mutating func setName(_ name: String) {
        self.name = name
    }

    mutating func setDateSupplied(_ dateSupplied: String) {
        self.dateSupplied = dateSupplied
    }

    mutating func setQuantityInStock(_ quantityInStock: Int) {
        self.quantityInStock = quantityInStock
    }

    mutating func setUnitPrice(_ unitPrice: Double) {
        self.unitPrice = unitPrice
    }
}
