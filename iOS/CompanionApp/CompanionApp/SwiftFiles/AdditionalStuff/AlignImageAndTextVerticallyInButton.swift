//
//  AlignImageAndTextVerticallyInButton.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-14.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import Foundation
import UIKit
// this allows us to vertically align image and text inside the button
extension UIButton {
  func alignImageAndTitleVertically(padding: CGFloat = 4.0) {
        let imageSize = imageView!.frame.size
        let titleSize = titleLabel!.frame.size
        let totalHeight = imageSize.height + titleSize.height + padding

        imageEdgeInsets = UIEdgeInsets(
            top: -(totalHeight - imageSize.height),
            left: 0,
            bottom: 0,
            right: -titleSize.width
        )

        titleEdgeInsets = UIEdgeInsets(
            top: 0,
            left: -imageSize.width,
            bottom: -(totalHeight - titleSize.height),
            right: 0
        )
    }
}
