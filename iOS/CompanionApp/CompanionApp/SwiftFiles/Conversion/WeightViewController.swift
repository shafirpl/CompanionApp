//
//  WeightViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-11.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class WeightViewController: UIViewController {
    
    @IBOutlet weak var weightPicker: UIPickerView!
    @IBOutlet weak var inputTextField: UITextField!
    @IBOutlet weak var outputLabel: UILabel!
    var conversionRate: Double = 2.20462
    var unit : String = "Lbs"
    let options = ["Kg To Lbs", "Lbs To Kg"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        inputTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        outputLabel.text = "0.0 Lbs"
        weightPicker.delegate = self
        weightPicker.dataSource = self
        
    }
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
    @IBAction func valueInputted(_ sender: Any) {
        let input: Double = Double(inputTextField.text!) ?? 0.0
        let result: Double = input * conversionRate
        var resultString:String = String(format: "%.2f", result)
        resultString += " "+unit
        outputLabel.text = resultString
        
    }
    
}

extension WeightViewController: UIPickerViewDataSource, UIPickerViewDelegate{
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
            conversionRate = 2.20462
            unit = "Lbs"
            inputTextField.text = ""
            outputLabel.text = "0.0 Lbs"
        } else{
            conversionRate = 0.453592
            unit = "Kg"
            inputTextField.text = ""
            outputLabel.text = "0.0 Kg"
        }
    }
    
    
}

