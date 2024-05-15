//
//  UIViewController+Extensions.swift
//  issue-tracker-01
//
//  Created by 조호근 on 5/13/24.
//

import UIKit

extension UIViewController {
    func createSwipeAction(title: String, color: UIColor, image: UIImage?, style: UIContextualAction.Style, handler: @escaping (UIContextualAction, UIView, @escaping (Bool) -> Void) -> Void) -> UIContextualAction {
        let action = UIContextualAction(style: style, title: title, handler: handler)
        action.backgroundColor = color

        action.image = image
        
        return action
    }
}
