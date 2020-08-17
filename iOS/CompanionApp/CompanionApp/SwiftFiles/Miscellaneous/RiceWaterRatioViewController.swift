//
//  RiceWaterRatioViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-14.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class RiceWaterRatioViewController: UIViewController {
    
    let options : [String] = ["Basmati Rice","Jasemine Rice","Chinigura Rice"]
    var requiredCups:Double = 2.0

    @IBOutlet weak var outputLabel: UILabel!
    @IBOutlet weak var optionPicker: UIPickerView!
    @IBOutlet weak var inputTextField: UITextField!
    let defaultString:String = "0.0 Cups of Water"
    override func viewDidLoad() {
        super.viewDidLoad()
        outputLabel.text = defaultString
        optionPicker.dataSource = self
        optionPicker.delegate = self
        inputTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
    }
    
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }

    @IBAction func inputChanged(_ sender: Any) {
        let input:Double = Double( inputTextField.text!) ?? 0.0
        let result: Double = input*requiredCups
        var resultString:String = String(format: "%.1f", result)
        resultString += " "+"Cups of Water"
        outputLabel.text = resultString
    }
    
    
}

extension RiceWaterRatioViewController: UIPickerViewDataSource, UIPickerViewDelegate{
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
        inputTextField.text = ""
        outputLabel.text = defaultString
        switch row {
        case 0,1:
            requiredCups = 1.5
            
        default:
            requiredCups = 2.0
        }
    }
    
    
}
