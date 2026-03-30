//
//  main.swift
//  ProductMgmtApp
//
//  Created by Htet Moe Phyu on 3/30/26.
//

import Foundation

func printProducts(_ products: [Product]) {
    
    /*
        ascending order of the Product Name and
        then descending order by the product Unit Price
     */
    let sortedProducts = products.sorted {
        if $0.name == $1.name {
            return $0.unitPrice > $1.unitPrice
        }
        return $0.name < $1.name
    }

    print("Printed in JSON Format")
    printJSON(sortedProducts)
    print("----------------------------------------")

    print("Printed in XML Format")
    printXML(sortedProducts)
    print("----------------------------------------")

    print("Printed in Comma-Separated Values (CSV) Format")
    printCSV(sortedProducts)
}


//MARK: - json format
func printJSON(_ products: [Product]) {
    let encoder = JSONEncoder()
    encoder.outputFormatting = [.prettyPrinted]

    do {
        let data = try encoder.encode(products)
        if let jsonString = String(data: data, encoding: .utf8) {
            print(jsonString)
        }
    } catch {
        print("Error encoding JSON: \(error.localizedDescription)")
    }
}

//MARK: - xml format
func printXML(_ products: [Product]) {
    print("<?xml version=\"1.0\"?>")
    print("<products>")

    for product in products {
        let xmlLine = """
          <product productId="\(product.productId)" name="\(product.name)" dateSupplied="\(product.dateSupplied)" quantityInStock="\(product.quantityInStock)" unitPrice="\(String(format: "%.2f", product.unitPrice))" />
        """
        print(xmlLine)
    }
    print("</products>")
}

//MARK: - csv format
func printCSV(_ products: [Product]) {
    for product in products {
        print("\(product.productId), \(product.name), \(product.dateSupplied), \(product.quantityInStock), \(String(format: "%.2f", product.unitPrice))")
    }
}

// Company data from the lab
let products: [Product] = [
    Product(
        productId: "31288741190182539912",
        name: "Banana",
        dateSupplied: "2026-01-24",
        quantityInStock: 124,
        unitPrice: 0.55
    ),
    Product(
        productId: "29274582650152771644",
        name: "Apple",
        dateSupplied: "2025-12-09",
        quantityInStock: 18,
        unitPrice: 1.09
    ),
    Product(
        productId: "91899274600128155167",
        name: "Carrot",
        dateSupplied: "2026-03-31",
        quantityInStock: 89,
        unitPrice: 2.99
    ),
    Product(
        productId: "31288741190182539913",
        name: "Banana",
        dateSupplied: "2026-02-13",
        quantityInStock: 240,
        unitPrice: 0.65
    )
]

printProducts(products) // Task 4 : print products

