//
//  NoteItemStruct.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-08.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import Foundation

struct noteItemStruct:Decodable {
    let noteId: String
    let title: String
    let description: String
}
