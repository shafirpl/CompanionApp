//
//  ConversionMainViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-10.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class ConversionMainViewController: UIViewController {

    @IBOutlet weak var distanceButton: UIButton!
    @IBOutlet weak var weightButton: UIButton!
    @IBOutlet weak var tempButton: UIButton!
    @IBOutlet weak var currencyButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        stylingButton(button: distanceButton)
        stylingButton(button: weightButton)
        stylingButton(button: tempButton)
        stylingButton(button: currencyButton)
        
    }
    
    func stylingButton(button:UIButton){
        button.layer.shadowRadius = 5
        button.layer.backgroundColor = UIColor.white.cgColor
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.shadowOffset = CGSize(width: 2, height: 2)
        button.layer.shadowOpacity = 1
        button.layer.borderWidth = 1
        button.layer.borderColor = UIColor.lightGray.cgColor
        // this is coming from AdditionalStuff folder with the same file name
        button.alignImageAndTitleVertically()
    }
    
}


