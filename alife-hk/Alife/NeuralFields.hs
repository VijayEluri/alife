module Alife.NeuralFields 
       where

import Alife.MathUtil

noDelayDelta :: (RealFrac a) => a -> [a] -> [a] -> [a] -> [a] -> [a]
noDelayDelta tao wi wa pi pa = map (/ tao) $ fixconv wa pa +. fixconv wi pi -. pa


