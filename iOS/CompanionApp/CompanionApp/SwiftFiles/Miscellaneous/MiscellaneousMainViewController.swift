//
//  MiscellaneousMainViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-12.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class MiscellaneousMainViewController: UIViewController {
    @IBOutlet weak var pcTempButton: UIButton!
    @IBOutlet weak var maintenanceButton: UIButton!
    
    @IBOutlet weak var cookingButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        stylingButton(button: pcTempButton)
        stylingButton(button: maintenanceButton)
        stylingButton(button: cookingButton)
    }
    
    func stylingButton(button:UIButton){
        button.alignImageAndTitleVertically()
        button.layer.shadowRadius = 5
        button.layer.backgroundColor = UIColor.white.cgColor
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.shadowOffset = CGSize(width: 2, height: 2)
        button.layer.shadowOpacity = 1
        button.layer.borderWidth = 1
        button.layer.borderColor = UIColor.lightGray.cgColor
    }

}

