//
//  CurrencyViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-12.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
var originalConversionRate:Double = 0.0
var conversionRate:Double = 0.0
var convertToUSD:Bool = false
let options : [String ] = ["USD To CAD","CAD To USD"]
var unit = "CAD"

class CurrencyViewController: UIViewController {

    @IBOutlet weak var rateLabel: UILabel!
    @IBOutlet weak var inputTextField: UITextField!
    @IBOutlet weak var outputLabel: UILabel!
    @IBOutlet weak var optionPickerView: UIPickerView!
    let url: String = "https://www.bankofcanada.ca/valet/observations/FXUSDCAD/json?recent=1"
    /*
     * We need to get the rate every time user comes to this screen. With viewDidLoad, the rate
     * will only be refreshed/fetched after we restart the app or close and start the app
     * so if we never close the app, the rate will never change. We don't want that
     */
    override func viewDidAppear(_ animated: Bool) {
        getCurrentRate()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        optionPickerView.delegate = self
        optionPickerView.dataSource = self
        inputTextField.addDoneButton(title: "Done", target: self, selector: #selector(tapDone(sender:)))
        outputLabel.text = "0.0 CAD"
        getCurrentRate()
        
    }
    

    
    func getCurrentRate(){
        AF.request(url,method: .get).responseJSON(completionHandler: {
            (response) in
            // print(response)
            /*
             * The JSON thing is coming from swiftyJson
             */
            let data = JSON(response.data!)

            /*
             * if we print the data, we will see that it has
             * a key "observations" that is an array of values, in which we
             * have the currency data. We need to grab that in order to
             * get the rate
             */
            let currencyData = data["observations"].arrayValue

            /*
             * here we are grabbing the data. Again print the data and see
             * how the conversion rate is nested
             */
            let curr = currencyData[0]["FXUSDCAD"]["v"]

            let tempVal = curr.string!
            
            originalConversionRate = Double(tempVal)!
            
            conversionRate = originalConversionRate
            let rateLabelText = "Today's Bank of Canada's rate is: " + String(originalConversionRate)
            self.rateLabel.text = rateLabelText
            
        })
    }
    
    @IBAction func valueInputted(_ sender: Any) {
        let input: Double = Double(inputTextField.text!) ?? 0.0
        let result: Double = input * conversionRate
        var resultString:String = String(format: "%.2f", result)
        resultString += " "+unit
        outputLabel.text = resultString
    }
    
    @objc func tapDone(sender: Any) {
        self.view.endEditing(true)
    }
    
}



extension CurrencyViewController: UIPickerViewDataSource, UIPickerViewDelegate{
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
            conversionRate = originalConversionRate
            unit = "CAD"
            inputTextField.text = ""
            outputLabel.text = "0.0 CAD"
        } else{
            conversionRate = (1/originalConversionRate)
            unit = "USD"
            inputTextField.text = ""
            outputLabel.text = "0.0 USD"
        }
    }
    
    
}


