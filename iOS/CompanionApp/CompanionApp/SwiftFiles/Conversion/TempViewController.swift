//
//  TempViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-12.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class TempViewController: UIViewController {

    @IBOutlet weak var optionPicker: UIPickerView!
    @IBOutlet weak var outputLabel: UILabel!
    @IBOutlet weak var inputTextField: UITextField!
    // shift+option+8 gives us the degree symbol
    let options = ["°C to °F", "°F to °C"]
    var unit:String = "°F"
    var convertToFar:Bool = true
    override func viewDidLoad() {
        super.viewDidLoad()
        optionPicker.delegate = self
        optionPicker.dataSource  = self
        outputLabel.text = "0 °F"
        inputTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))

        // Do any additional setup after loading the view.
    }
    
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
    /*
     * This function runs when the user enters something on the textfield, I made a video on
     */
    @IBAction func valueInputted(_ sender: Any) {
        let input: Double = Double(inputTextField.text!) ?? 0.0
        var result: Double = 0.0
        if(convertToFar){
            result = input * 9 / 5 + 32
        }
        else{
            result = 5 / 9 * (input-32)
        }
        var resultString:String = String(format: "%.2f", result)
        resultString += " "+unit
        outputLabel.text = resultString
    }
    

}

extension TempViewController: UIPickerViewDataSource, UIPickerViewDelegate{
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
            convertToFar = true
            unit = "°F"
            inputTextField.text = ""
            outputLabel.text = "0.0 °F"
        } else{
            convertToFar = false
            unit = "°C"
            inputTextField.text = ""
            outputLabel.text = "0.0 °C"
        }
    }
    
    
}
