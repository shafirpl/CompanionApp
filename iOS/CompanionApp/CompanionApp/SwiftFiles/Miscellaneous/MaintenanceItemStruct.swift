//
//  MaintenanceItemStruct.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-13.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import Foundation

struct maintenanceItemStruct:Decodable {
    // question mark is to fix the initialization issue
    // i could also do price = 0 or some empty initialization
    // without this i cannot just declare an empty variable/struct
    // item
    var price: Double?
    var date: String?
    var shopName: String?
    var place: String?
    var title: String?
    var description: String?
    var odometer: Int?
}
