//
//  NoteDetailsViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-08.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire

class NoteDetailsViewController: UIViewController {
    @IBOutlet weak var noteEditTitle: UITextField!
    let urlString = "http://138.68.61.175:5500/notes/"
        let alert = UIAlertController(title: "Note Edited", message: nil, preferredStyle: .alert)
    
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
        
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))

    }
    // 2
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
    @IBAction func confirmEditAction(_ sender: Any) {

        let url = urlString + noteItem!.noteId
        //print(url)
        
        let parameters = ["title": noteEditTitle.text,
                          "description": noteEditDetail.text]
        /*
         * Unlike volley request where we make the jsonObject and then make the request,
         * here we just populate the parameters, and then do JSONEncoding.default to make
         * the parameters act as the body of our request
         */
        // as long as we import alamofire, we can use this as AF
        AF.request(url,method: .put,parameters: parameters as Parameters,encoding: JSONEncoding.default).responseJSON { response in
            switch response.result{
            case .success(_):
                self.present(self.alert, animated: true)
            case .failure(_):
                self.alert.title = "Server Error"
                self.present(self.alert, animated: true)
            }

        }
    }
    
}
