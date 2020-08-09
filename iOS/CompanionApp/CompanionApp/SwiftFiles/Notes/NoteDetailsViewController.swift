//
//  NoteDetailsViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-08.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class NoteDetailsViewController: UIViewController {
    @IBOutlet weak var noteEditTitle: UITextField!
    
    @IBOutlet weak var confirmEditButton: UIButton!
    @IBOutlet weak var noteEditDetail: UITextView!
    var noteItem:noteItemStruct?
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // styling the button
        // adding paddings
        confirmEditButton.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
        // confirmEditButton.layer.cornerRadius = 7
        
        // adding border to text view
        noteEditDetail.layer.borderWidth = 1
        noteEditDetail.layer.borderColor = UIColor.black.cgColor
        
        // adding done button to textview
        noteEditDetail.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        
        noteEditTitle.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        
        noteEditTitle.text = noteItem?.title
        noteEditDetail.text = noteItem?.description

    }
    // 2
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }

}
