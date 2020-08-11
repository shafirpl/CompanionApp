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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        distanceButton.layer.shadowRadius = 5
        distanceButton.layer.backgroundColor = UIColor.white.cgColor
        distanceButton.layer.shadowColor = UIColor.black.cgColor
        distanceButton.layer.shadowOffset = CGSize(width: 2, height: 2)
        distanceButton.layer.shadowOpacity = 1


        distanceButton.layer.borderWidth = 1
        distanceButton.layer.borderColor = UIColor.lightGray.cgColor
        
    }
    



}
