//
//  NewRecordViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-13.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire

class NewRecordViewController: UIViewController {
        @IBOutlet weak var titleTextField: UITextField!
    let url: String = "http://138.68.61.175:5500/maintenance"
    
    @IBOutlet weak var shopTextField: UITextField!
    
    @IBOutlet weak var odometerTextField: UITextField!
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var dateTextField: UITextField!
    @IBOutlet weak var priceTextField: UITextField!
    @IBOutlet weak var placeTextField: UITextField!
    
    let alert = UIAlertController(title: "Record Added", message: nil, preferredStyle: .alert)
    
    @IBOutlet weak var addRecordButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()

        addRecordButton.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
        
        // adding border to textview
        descriptionTextView.layer.borderWidth = 1
        descriptionTextView.layer.borderColor = UIColor.black.cgColor
        
        // adding done buttons
        titleTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        shopTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        placeTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        odometerTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        priceTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        dateTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        descriptionTextView.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        
        // this is for showing keyboard when user is on the bottom part

        
    }
    


    

    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
    @IBAction func addRecord(_ sender: Any) {
        let price: Double = Double(priceTextField.text!) ?? 0.0
        let odometer: Int = Int(odometerTextField.text!) ?? 0
        let parameters = ["date": dateTextField?.text ?? "", "shopName":shopTextField?.text ?? "",
                          "description": descriptionTextView?.text ?? "",
                          "place": placeTextField?.text ?? "",
                          "title": titleTextField?.text ?? "",
                          "price": price,
                          "odometer": odometer] as [String : Any]
        AF.request(url,method: .post, parameters: parameters as Parameters,encoding: JSONEncoding.default).responseJSON{ response in
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
