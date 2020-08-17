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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))

        // Do any additional setup after loading the view.
        addPcButton.contentEdgeInsets = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
    }
    
    @IBAction func addNewPcAction(_ sender: Any) {
        let compName:String = pcNameLabel.text ?? ""
        let ipAddress:String = ipAddressLabel.text ?? ""
        let parameters = ["compName":compName,"ipAddress":ipAddress]
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
