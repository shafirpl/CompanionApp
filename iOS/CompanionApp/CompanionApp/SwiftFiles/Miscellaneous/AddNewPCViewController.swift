//
//  AddNewPCViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-17.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire



class AddNewPCViewController: UIViewController {
    @IBOutlet weak var pcNameLabel: UITextField!
    @IBOutlet weak var addPcButton: UIButton!
    let url = "http://138.68.61.175:5500/ip"
    @IBOutlet weak var ipAddressLabel: UITextField!
    let alert = UIAlertController(title: "PC Added", message: nil, preferredStyle: .alert)
    var sourceCompName:String?
    var sourceIpAddress:String?
    var id:String?
    var editingMode:Bool! = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        // print(editingMode)
        if(editingMode){
            pcNameLabel.text = sourceCompName
            ipAddressLabel.text = sourceIpAddress
            addPcButton.setTitle("Confirm Edit", for: .normal)
            // https://stackoverflow.com/questions/1560081/how-can-i-create-a-uicolor-from-a-hex-string
            let green = #colorLiteral(red: 0.1098039216, green: 0.4352941176, blue: 0.1960784314, alpha: 1)
            addPcButton.layer.backgroundColor = green.cgColor
        }

        // Do any additional setup after loading the view.
        addPcButton?.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
    }
    
    @IBAction func addNewPcAction(_ sender: Any) {
        let compName:String = pcNameLabel?.text ?? ""
        let ipAddress:String = ipAddressLabel?.text ?? ""
        let parameters = ["compName":compName,"ipAddress":ipAddress]
        if (editingMode){
            self.alert.title = "PC Edited"
            let urlString = url+"/"+self.id!
            AF.request(urlString, method: .put,parameters: parameters as Parameters ,encoding: JSONEncoding.default).responseJSON(completionHandler: {
                response in
                print(response)
                switch response.result{
                    case .success(_):
                        self.present(self.alert, animated: true)
                    case .failure(_):
                        self.alert.title = "Server Error"
                        self.present(self.alert, animated: true)
                }
            })
            
        }
        else{
            AF.request(url,method: .post,parameters: parameters as Parameters ,encoding: JSONEncoding.default).responseJSON(completionHandler: {
                response in
                print(response)
                switch response.result{
                    case .success(_):
                        self.present(self.alert, animated: true)
                    case .failure(_):
                        self.alert.title = "Server Error"
                        self.present(self.alert, animated: true)
                }
            })
        }

    }
    
    


}
