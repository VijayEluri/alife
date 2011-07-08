module Alife.RecurUtil 
       (
         ana
       ) where

ana :: (RealFrac a, Integral b) => ((b, b) -> (a, (b, b))) -> ((b, b) -> Bool) -> (b, b) -> [a] 
ana g p x 
  | p x = []
  | otherwise = y:ana g p x' where (y, x') = g x
