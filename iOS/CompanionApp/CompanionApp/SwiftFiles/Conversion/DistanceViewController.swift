//
//  DistanceViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-10.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class DistanceViewController: UIViewController {

    @IBOutlet weak var distancePicker: UIPickerView!
    
    @IBOutlet weak var distanceLabel: UILabel!
    @IBOutlet weak var distanceTextField: UITextField!
    
    
    let options = ["Miles To KM","KM To Miles"]
    var conversionRate:Double = 1.60934
    var unit: String = "KM"
    override func viewDidLoad() {
        super.viewDidLoad()
        distanceTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        distancePicker.dataSource = self
        distancePicker.delegate = self
        distanceLabel.text = "0.0 KM"

        // Do any additional setup after loading the view.
    }
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
    @IBAction func inputChanged(_ sender: Any) {
        let input:Double = Double( distanceTextField.text!) ?? 0.0
        let result: Double = input*conversionRate
        var resultString:String = String(format: "%.2f", result)
        resultString += " "+unit
        distanceLabel.text = resultString
        
    }
    
}



extension DistanceViewController: UIPickerViewDataSource, UIPickerViewDelegate{
    /*
    * https://www.youtube.com/watch?v=HkDDGfMiuOA
    * 3:25
    */
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    /*
    * https://www.youtube.com/watch?v=HkDDGfMiuOA
    * 3:48
    */
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return options.count
    }
    /*
    * https://www.youtube.com/watch?v=HkDDGfMiuOA
    * 3:59
    */
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return options[row]
    }
    /*
    * https://www.youtube.com/watch?v=HkDDGfMiuOA
    * 4:35
    */
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if(row == 0){
            conversionRate = 1.60934
            unit = "KM"
            distanceTextField.text = ""
            distanceLabel.text = "0.0 KM"
        } else{
            conversionRate = 0.621371
            unit = "Miles"
            distanceTextField.text = ""
            distanceLabel.text = "0.0 Miles"
        }
    }
    
    
}

