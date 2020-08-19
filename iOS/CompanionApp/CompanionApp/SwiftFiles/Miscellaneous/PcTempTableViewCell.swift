//
//  PcTempTableViewCell.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-17.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class PcTempTableViewCell: UITableViewCell {

    @IBOutlet weak var compNameLabel: UILabel!
    @IBOutlet weak var ipAddressLabel: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
