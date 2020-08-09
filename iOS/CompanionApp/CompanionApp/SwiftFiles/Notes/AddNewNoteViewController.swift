//
//  AddNewNoteViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-09.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire

class AddNewNoteViewController: UIViewController {

    @IBOutlet weak var newNoteDetails: UITextView!
    @IBOutlet weak var newNoteTitle: UITextField!
    @IBOutlet weak var addNewNoteButton: UIButton!
    let urlString = "http://138.68.61.175:5500/notes"
    
    let alert = UIAlertController(title: "Note Added", message: nil, preferredStyle: .alert)
    override func viewDidLoad() {
        super.viewDidLoad()

        // styling the button
        // adding paddings
        addNewNoteButton.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
        
        // adding border to text view
        newNoteDetails.layer.borderWidth = 1
        newNoteDetails.layer.borderColor = UIColor.black.cgColor
        
        // adding done buttons
        newNoteDetails.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        
        newNoteTitle.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        
        
    }
    
    @IBAction func addNewNoteAction(_ sender: Any) {
        let uuid = UUID().uuidString
        /*
         * Unlike volley request where we make the jsonObject and then make the request,
         * here we just populate the parameters, and then do JSONEncoding.default to make
         * the parameters act as the body of our request
         */
        let parameters = ["title":newNoteDetails?.text,"description": newNoteDetails?.text,"noteId":uuid]
        // as long as we import alamofire, we can use this as AF
        AF.request(urlString,method: .post,parameters: parameters as Parameters,encoding: JSONEncoding.default).responseJSON { response in
            switch response.result{
            case .success(_):
                self.present(self.alert, animated: true)
            case .failure(_):
                self.alert.title = "Server Error"
                self.present(self.alert, animated: true)
            }

        }
        
    }
    
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    

}
