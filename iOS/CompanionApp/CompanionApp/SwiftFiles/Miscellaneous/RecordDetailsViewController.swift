//
//  RecordDetailsViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-13.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire

class RecordDetailsViewController: UIViewController {
    @IBOutlet weak var titleTextField: UITextField!
    @IBOutlet weak var shopTextField: UITextField!
    @IBOutlet weak var placeTextField: UITextField!
    @IBOutlet weak var odometerTextField: UITextField!
    @IBOutlet weak var priceTextField: UITextField!
    @IBOutlet weak var dateTextField: UITextField!
    @IBOutlet weak var descriptionTextView: UITextView!
    
    @IBOutlet weak var confirmEditButton: UIButton!
    let alert = UIAlertController(title: "Record Edited", message: nil, preferredStyle: .alert)
    
    var recordItem: maintenanceItemStruct?
    
    let urlString:String = "http://138.68.61.175:5500/maintenance"

    override func viewDidLoad() {
        super.viewDidLoad()
        //print(recordItem)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        
        confirmEditButton.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
        
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
        
        titleTextField.text = recordItem?.title
        shopTextField.text = recordItem?.date
        placeTextField.text = recordItem?.place
        dateTextField.text = recordItem?.date
        descriptionTextView.text = recordItem?.description
        /*
         * I was having issue with this one
         * let odometer: String = String(recordItem?.odometer!)
         * so someone suggested this:
         * https://www.reddit.com/r/iOSProgramming/comments/i9k8e1/how_to_fix_initializer_init_requires_that_int/g1flcuf/
         * this fixes the issue
         */
        //
        let odometerString = "\(recordItem?.odometer ?? 0)"
       
        odometerTextField.text = odometerString
        descriptionTextView.text = recordItem?.description
        let priceString = "\(recordItem?.price ?? 0)"
        priceTextField.text = priceString
        
        

        // Do any additional setup after loading the view.
    }
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }

    @IBAction func confirmEditAction(_ sender: Any) {
        let url:String = urlString + "/" + (recordItem?.id!)!
        let price: Double = Double(priceTextField.text!) ?? 0.0
        let odometer: Int = Int(odometerTextField.text!) ?? 0
        let parameters = ["date": dateTextField?.text ?? "", "shopName":shopTextField?.text ?? "",
            "description": descriptionTextView?.text ?? "",
            "place": placeTextField?.text ?? "",
            "title": titleTextField?.text ?? "",
            "price": price,
            "odometer": odometer] as [String : Any]
            AF.request(url,method: .put, parameters: parameters as Parameters,encoding: JSONEncoding.default).responseJSON{ response in
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


